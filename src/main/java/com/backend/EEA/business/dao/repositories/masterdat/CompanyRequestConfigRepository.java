package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.CompanyRequestConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRequestConfigRepository extends GenericJPARepository<CompanyRequestConfig> {
    CompanyRequestConfig findByAdministrativeIdAndCompanyActivityIdAndRequestTypeId(Long administrativeId, Long activityId, Long RequestId);
    CompanyRequestConfig findByAdministrativeIdAndCompanyActivityId(Long administrativeId, Long activityId);

    CompanyRequestConfig findByCompanyActivityIdAndRequestTypeId(Long companyActivity, Long requestType);
}
