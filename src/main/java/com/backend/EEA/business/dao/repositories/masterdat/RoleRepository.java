package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericJPARepository<Role> {
     public Role findByCode(String code);
}
