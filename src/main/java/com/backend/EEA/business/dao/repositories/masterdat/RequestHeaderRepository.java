package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RequestHeaderRepository extends GenericJPARepository<RequestHeader> {

    List<RequestHeader> findByEntityIdAndId(Long entityId, Long id);

    List<RequestHeader> findByNameAndCodeAndEntityId(String name, String code, Long entityId);

    List<RequestHeader> findByEntityIdAndNameAndCodeAndIdNot(Long entityId, String name, String code, Long Id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE RequestHeader r set status =?2 where r.id = ?1")
    Integer updateRequestStatus(Long requestId, CustomerRequestStatus status);
    @Transactional
    @Modifying
    @Query(value = "UPDATE RequestHeader r set r.category =1 where r.id = ?1")
    Integer updateRequestRdfCategory(Long requestId);

}
