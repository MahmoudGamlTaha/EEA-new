package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Harbor;
import org.springframework.stereotype.Repository;

@Repository
public interface HarborRepository extends GenericJPARepository<Harbor> {

}
