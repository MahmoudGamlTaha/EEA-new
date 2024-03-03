package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Rating;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestFeesRepository extends GenericJPARepository<RequestFees> {
   Optional<RequestFees> findByRequestId(Long requestId);
}
