package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.CoalType;
import com.backend.EEA.model.entity.masterdata.Currency;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends GenericJPARepository<Currency> {
}
