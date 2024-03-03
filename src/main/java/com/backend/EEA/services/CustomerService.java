package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.RoleRepository;
import com.backend.EEA.business.dao.repositories.masterdat.UserRepository;
import com.backend.EEA.business.dao.repositories.masterdat.UserRoleRepository;
import com.backend.EEA.business.dao.specifications.masterdata.CustomerSpecifications;
import com.backend.EEA.business.dao.specifications.masterdata.UserSpecifications;
import com.backend.EEA.common.security.Roles;
import com.backend.EEA.exceptions.BusinessException;
import com.backend.EEA.mapper.masterdata.UserMapper;
import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.model.entity.masterdata.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends BaseService<User, UserDto, UserSearchForm> {

     UserRepository userRepository;
     @Autowired
     UserMapper userMapper;
     @Autowired
     RoleRepository roleRepository;

     @Autowired
     UserRoleRepository userRoleRepository;

    public CustomerService(UserRepository userRepository){
        super(userRepository);
    }
    @Override
    protected Specification<User> buildSpecification(UserSearchForm searchParams) {
        if(searchParams == null){
            searchParams = new UserSearchForm();
            searchParams.setRoleCode(Roles.CUSTOMER.name());
        }
        searchParams.setRoleCode(Roles.CUSTOMER.name());
        return CustomerSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<UserDto> mapDataListToDtoList(List<User> list) {
        return list.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    protected UserDto prepareEntityToDto(User object) {
        return userMapper.toUserDto(object);
    }

    @Override
    protected User prepareDtoToEntity(UserDto object) {
        return userMapper.toUser(object);
    }
    @Override
    protected void doAfterCreate(User entity) throws BusinessException {
        UserRole userRole = new UserRole();
        userRole.setEntityId(entity.getEntityId());
        userRole.setChangerId(entity.getChangerId());
        userRole.setRoleId(roleRepository.findByCode(Roles.CUSTOMER.name()).getId());
        userRole.setUserId(entity.getId());
        this.userRoleRepository.save(userRole);
    }
    @Override
    protected void setIdBeforeUpdate(long id, User object) {
         object.setId(id);
    }

    @Override
    public UserSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, UserSearchForm.class);
    }
}
