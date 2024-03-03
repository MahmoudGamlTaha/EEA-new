package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends GenericJPARepository<Country> {

}
