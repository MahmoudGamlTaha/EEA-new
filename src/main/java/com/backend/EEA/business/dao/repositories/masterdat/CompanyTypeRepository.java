package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.CompanyType;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends GenericJPARepository<CompanyType> {
     public CompanyType findByCode(String code);
}
