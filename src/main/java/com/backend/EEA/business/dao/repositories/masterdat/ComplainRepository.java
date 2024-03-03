package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.CompanyType;
import com.backend.EEA.model.entity.masterdata.Complains;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends GenericJPARepository<Complains> {
    ;
}
