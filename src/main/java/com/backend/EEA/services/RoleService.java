package com.backend.EEA.services;

import com.backend.EEA.business.dao.repositories.masterdat.RoleRepository;
import com.backend.EEA.business.dao.specifications.masterdata.RoleSpecifications;
import com.backend.EEA.mapper.masterdata.RoleMapper;
import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.dto.search.RoleSearchForm;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService extends BaseService<Role, RoleDto, RoleSearchForm> {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository);
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    protected Specification<Role> buildSpecification(RoleSearchForm searchParams) {
        return RoleSpecifications.buildSpecifications(searchParams);
    }

    @Override
    protected List<RoleDto> mapDataListToDtoList(List<Role> list) {
        return list.stream().map(roleMapper::toRoleDto).collect(Collectors.toList());
    }

    @Override
    protected RoleDto prepareEntityToDto(Role object) {
        return roleMapper.toRoleDto(object);
    }

    @Override
    protected Role prepareDtoToEntity(RoleDto object) {
        return roleMapper.toRole(object);
    }

    @Override
    protected void setIdBeforeUpdate(long id, Role object) {
          object.setId(id);
    }

    @Override
    public RoleSearchForm prepareSearchPojo(String searchQuery) throws JsonProcessingException {
        return objectMapper.readValue(searchQuery, RoleSearchForm.class);
    }
}
