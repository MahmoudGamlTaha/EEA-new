package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Rdf;
import com.backend.EEA.model.entity.masterdata.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RdfRepository extends GenericJPARepository<Rdf> {
    Optional<Rdf> findByRequestId(Long requestId);
}
