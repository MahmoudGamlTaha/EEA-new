package com.backend.EEA.services;

import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.model.dto.masterdata.UserSessionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.common.exceptions.ResourceNotFoundException;
import com.backend.EEA.model.dto.search.BaseSearchForm;
import com.backend.EEA.model.entity.BaseEntity;
import com.backend.EEA.model.entity.BaseHeaderEntityGen;
import com.backend.EEA.model.entity.BaseHeaderEntityIntegrate;
import com.backend.EEA.model.enums.ResponseMessageEnum;
import com.backend.EEA.model.enums.SortTypeEnum;
import com.backend.EEA.model.pojos.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity, X, Z extends BaseSearchForm> {

    @Value("${entityId}")
    private Long entityId;

    @Autowired
    protected ObjectMapper objectMapper;
    protected GenericJPARepository<T> genericJpARepository;

    public BaseService(GenericJPARepository<T> genericJpARepository) {
        this.genericJpARepository = genericJpARepository;
    }

    public List<X> findListBySpecifications(Z searchObj) {
        List<T> resultList = genericJpARepository.findAll(buildSpecification(searchObj));
        return mapDataListToDtoList(resultList);
    }

    public Object findListBySpecificationsWithPagination(Z searchObj) {
        Page<T> entityPage = findEntityPageBySpecifications(searchObj);
        return buildPageDto(entityPage);
    }

    public X findOne(long id) throws ResourceNotFoundException {
        Optional<T> optional = this.genericJpARepository.findById(id);
        if (optional.isPresent()) return prepareEntityToDto(optional.get());
        else throw new ResourceNotFoundException(ResponseMessageEnum.NOT_FOUND.getMessage());
    }
    @Transactional
    public X createEntity(X dtoPojo) throws Exception {
        T entity = prepareDtoToEntity(dtoPojo);
        setEntityId(entity, getEntityId());
        setChangerId(entity, getLoggedInUserId());
        setLastUpdateDate(entity, new Date());
        doBeforeCreate(entity);
        entity = saveEntity(entity);
        doAfterCreate(entity);
        return prepareEntityToDto(entity);
    }
    public void setDataBeforeEdit(T entity, X oldDto ){

    }
    public void editEntity(long id, X dtoPojo) throws Exception {
        X dto = findOne(id);
        T entity = prepareDtoToEntity(dtoPojo);
        setEntityId(entity, getEntityId());
        setChangerId(entity, getLoggedInUserId());
        setLastUpdateDate(entity, new Date());
        setIdBeforeUpdate(id, entity);
        doBeforeEdit(entity);
        setDataBeforeEdit(entity, dto);
        saveEntity(entity);
        doAfterUpdate(entity);
    }

    protected T saveEntity(T entity) throws Exception {
        return genericJpARepository.save(entity);
    }

    protected void setEntityId(T entity, Long entId) {
        if (entity instanceof BaseHeaderEntityGen)
            ((BaseHeaderEntityGen) entity).setEntityId(entId);
        else if (entity instanceof BaseHeaderEntityIntegrate)
            ((BaseHeaderEntityIntegrate) entity).setEntityId(entId);
    }

    protected void setChangerId(T entity, Long id) {
        entity.setChangerId(id);
    }

    protected void setLastUpdateDate(T entity, Date lastUpdateDate) {
        entity.setLastUpdateDate(lastUpdateDate);
    }

    public void delete(long id) throws Exception {
        if (genericJpARepository.existsById(id)) {
            doBeforeDelete(id);
            genericJpARepository.deleteById(id);
            doAfterDelete(id);
        } else throw new BusinessException(ResponseMessageEnum.NOT_FOUND.getMessage());
    }

    public long getEntityId() {
        return entityId;
    }

    protected long getLoggedInUserId() {
        UserSessionDto userSessionDto= null;
         if(SecurityContextHolder.getContext().getAuthentication() != null )
             if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser") )
                 userSessionDto = (UserSessionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userSessionDto==null? 1L : userSessionDto.getId();
    }

    protected UserSessionDto getLoggedInUserSession() {
        UserSessionDto userSessionDto = null;
        if(SecurityContextHolder.getContext().getAuthentication() != null)
         userSessionDto = (UserSessionDto)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userSessionDto == null?new UserSessionDto("sys","",1L,null):userSessionDto;
    }

    protected abstract Specification<T> buildSpecification(Z searchParams);

    protected abstract List<X> mapDataListToDtoList(List<T> list);

    protected abstract X prepareEntityToDto(T object);

    protected abstract T prepareDtoToEntity(X object);

    protected abstract void setIdBeforeUpdate(long id, T object);

    public abstract Z prepareSearchPojo(String searchQuery) throws JsonProcessingException;

    protected void doBeforeCreate(T entity) throws BusinessException {
    }

    protected void doBeforeEdit(T entity) throws BusinessException {
    }

    protected void doBeforeDelete(Long id) throws BusinessException {
    }

    protected void doAfterCreate(T entity) throws BusinessException {
    }

    protected void doAfterUpdate(T entity) throws BusinessException {
    }

    protected void doAfterDelete(Long id) throws BusinessException {
    }

    private Page<T> findEntityPageBySpecifications(Z searchParPojo) {
        Page<T> entityPage = null;
        Specification<T> specification = buildSpecification(searchParPojo);
        if (searchParPojo != null && searchParPojo.getPage() != null && searchParPojo.getSize() != null) {
            entityPage = genericJpARepository.findAll(specification, buildPageRequest(searchParPojo));
        }
        return entityPage;
    }

    private PageDto buildPageDto(Page<T> entityPage) {
        PageDto pageDto = new PageDto();
        if (entityPage != null) {
            pageDto.setData(mapDataListToDtoList(entityPage.getContent()));
            pageDto.setTotalElements(entityPage.getTotalElements());
            pageDto.setTotalPages(entityPage.getTotalPages());
        }
        return pageDto;
    }

    private PageRequest buildPageRequest(Z searchParPojo) {
        int page = 0;
        int size = 1;
        if (searchParPojo != null && searchParPojo.getPage() != null && searchParPojo.getSize() != null) {
            page = searchParPojo.getPage();
            size = searchParPojo.getSize();
        }
        Sort sortBy = buildSortField(searchParPojo);

        return sortBy != null ? PageRequest.of(page, size, sortBy) : PageRequest.of(page, size);
    }

    private Sort buildSortField(Z searchParPojo) {
        Sort sortBy = null;
        if (searchParPojo != null && searchParPojo.getSortPojo() != null) {
            if (searchParPojo.getSortPojo().getFieldName() != null && !searchParPojo.getSortPojo().getFieldName().isEmpty()) {
                String sortingField = searchParPojo.getSortPojo().getFieldName();
                sortBy = Sort.by(findSortDirection(searchParPojo.getSortPojo().getSortDirection()), sortingField);
            }
        }
        return sortBy;
    }

    private Sort.Direction findSortDirection(String sortDir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDir != null && sortDir.equalsIgnoreCase(SortTypeEnum.DESC.getSortType())) {
            direction = Sort.Direction.DESC;
        }
        return direction;
    }

}
