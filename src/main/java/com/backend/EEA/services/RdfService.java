package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.*;
import com.backend.EEA.business.dao.specifications.masterdata.RdfSpecifications;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.CoalTypeMapper;
import com.backend.EEA.mapper.masterdata.RdfMapper;
import com.backend.EEA.model.dto.masterdata.CoalTypeDto;
import com.backend.EEA.model.dto.masterdata.RdfDto;
import com.backend.EEA.model.dto.masterdata.UserSessionDto;
import com.backend.EEA.model.dto.search.CoalTypeSearchForm;
import com.backend.EEA.model.dto.search.RdfSearchForm;
import com.backend.EEA.model.entity.masterdata.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RdfService extends BaseService<Rdf, RdfDto, RdfSearchForm> {

    RdfRepository rdfRepository;
    RdfMapper rdfMapper;
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AdministrativeStructureRepository administrativeStructureRepository;


    @Autowired
    private RequestHeaderRepository requestHeaderRepository;

    public RdfService(RdfRepository rdfRepository, RdfMapper rdfMapper) {
        super(rdfRepository);
        this.rdfMapper = rdfMapper;
        this.rdfRepository = rdfRepository;
    }

    @Override
    protected Specification<Rdf> buildSpecification(RdfSearchForm searchParams) {
        UserSessionDto userSessionDto = getLoggedInUserSession();
        if(userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("customer")))
            searchParams.setRequesterId(userSessionDto.getId());
        else if(userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("user"))||
                userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("department_supervisor"))||
                userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("admin")) ||
                userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("department_agent")) || userSessionDto.getAuthorities().contains(new SimpleGrantedAuthority("Notary"))){

            Long administrativeId =  getLoggedInUserSession().getAdministrativeId();
            AdministrativeStructure administrativeStructure = null;
            if(administrativeId != null)
              searchParams.setAdministrativeId(new Long[]{userSessionDto.getAdministrativeId()});
        }



        return RdfSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<RdfDto> mapDataListToDtoList(List<Rdf> list) {
        return list.stream().map(rdfMapper::toRDFDto).collect(Collectors.toList());
    }

    @Override
    protected RdfDto prepareEntityToDto(Rdf object) {
        return rdfMapper.toRDFDto(object);
    }
    @Override
    public void doBeforeCreate(Rdf rdf) {
        RequestHeader requestHeader = this.requestHeaderRepository.findById(rdf.getRequestId()).orElse(null);
         if (requestHeader == null){
             throw new BusinessException("invalid rdf org request");
         }
        rdf.setCompanyId(requestHeader.getCompanyId());
        rdf.setRequesterId(getLoggedInUserId());
        if (rdf.getInvoices() != null) {
            rdf.getInvoices().forEach(invoice -> {
                if (invoice.getFileField() == null)
                    throw new BusinessException("attachment rdf error");
                if (invoice.getId() == null)
                    throw new BusinessException("attachment rdf error id");
            });
        }
    }
    @Override
    public void doAfterCreate(Rdf rdf){
        List<Attachment> attachments = new LinkedList<>();
        rdf.getInvoices().stream().forEach(attachment -> {
            Attachment attachment1 = attachmentRepository.findById(attachment.getId()).orElse(null);
            attachment1.setRdfId(rdf.getId());
            attachment1.setTemp(false);
            attachment1.setFileField(attachment.getFileField());
            attachment1.setValidFromDate(attachment.getValidFromDate());
            attachment1.setValidToDate(attachment.getValidToDate());
            attachment1.setLastUpdateDate(new Date());
            attachments.add(attachment1);
        });

        this.attachmentRepository.saveAll(attachments);
        this.requestHeaderRepository.updateRequestRdfCategory(rdf.getRequestId());
    }
    public Rdf findRdfByRequestId(Long requestId){
        RequestHeader requestHeader = this.requestHeaderRepository.findById(requestId).orElse(null);

       return this.rdfRepository.findByRequestId(requestId).orElse(null);
    }

    @Override
    protected Rdf prepareDtoToEntity(RdfDto object) {
        return rdfMapper.toRDF(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Rdf object) {
          object.setId(id);
    }

    @Override
    public RdfSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, RdfSearchForm.class);
    }


}
