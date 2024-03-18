package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Harbor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarborRepository extends GenericJPARepository<Harbor> {

    List<Harbor> findByIdIn(List<Long> ids);
}
