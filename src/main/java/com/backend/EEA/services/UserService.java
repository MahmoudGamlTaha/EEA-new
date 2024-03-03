package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.UserRoleRepository;
import com.backend.EEA.model.dto.masterdata.UserSessionDto;
import com.backend.EEA.model.entity.masterdata.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.backend.EEA.business.dao.repositories.masterdat.UserRepository;
import com.backend.EEA.business.dao.specifications.masterdata.UserSpecifications;
import com.backend.EEA.mapper.masterdata.UserMapper;
import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService<User, UserDto, UserSearchForm> {


    private final UserMapper userMapper;
    @Autowired
    private UserRoleRepository userRoleRepository;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userMapper = userMapper;
    }

    @Override
    protected Specification<User> buildSpecification(UserSearchForm searchParams) {
        return UserSpecifications.buildSpecifications(searchParams);
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
    protected void setIdBeforeUpdate(long id, User object) {
        object.setId(id);
    }

    @Override
    public UserSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, UserSearchForm.class);
    }
    @Override
    public void doAfterCreate(User entity){
        Long userId = getLoggedInUserId();
           if(entity.getUserRoles() != null && entity.getUserRoles().size() > 0){
               entity.getUserRoles().forEach(role ->{
                   UserRole userRole = new UserRole();
                   userRole.setRoleId(role.getRoleId());
                   userRole.setChangerId(userId);
                   userRole.setEntityId(getEntityId());
                   userRole.setUserAssigned(entity);
                   userRole.setUserId(entity.getId());
                   userRoleRepository.save(userRole);
               });

           }
    }
}
