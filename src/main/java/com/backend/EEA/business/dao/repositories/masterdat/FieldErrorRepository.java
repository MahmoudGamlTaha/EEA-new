package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.FieldError;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface FieldErrorRepository extends GenericJPARepository<FieldError> {
   List<FieldError> findByRequestId(Long requestId);
   @Modifying
   @Transactional
   Long deleteAllByIdInAndRequestId(List<Long> serializables, Long requestId);
}
