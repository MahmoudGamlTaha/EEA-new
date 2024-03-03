package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Country;
import com.backend.EEA.model.entity.masterdata.Government;
import org.springframework.stereotype.Repository;

@Repository
public interface GovernmentRepository extends GenericJPARepository<Government> {

}
