package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.operation.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoggerRepository extends GenericJPARepository<Logger> {
   List<Logger> findByTableAndRowId(String table, Long id);
}
