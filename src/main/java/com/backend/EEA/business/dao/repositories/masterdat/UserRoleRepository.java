package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends GenericJPARepository<UserRole> {
     public UserRole findByUserId(Long userId);
}
