package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Harbor;
import com.backend.EEA.model.entity.masterdata.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends GenericJPARepository<Rating> {

}
