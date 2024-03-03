package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.RequestHeaderTracking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestHeaderTrackingRepository extends GenericJPARepository<RequestHeaderTracking> {
   RequestHeaderTracking findTopByRequestIdOrderByActionOrderDesc(Long requestId);
   List<RequestHeaderTracking> findByRequestIdOrderByActionOrderDesc(Long requestId);

}
