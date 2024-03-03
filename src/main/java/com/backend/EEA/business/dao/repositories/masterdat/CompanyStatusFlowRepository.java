package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.CompanyRequestConfig;
import com.backend.EEA.model.entity.masterdata.CompanyStatusFlowConfig;
import com.backend.EEA.model.enums.CustomerRequestStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyStatusFlowRepository extends GenericJPARepository<CompanyStatusFlowConfig> {
      CompanyStatusFlowConfig findByCustomerRequestStatusAndRequestTypeIdAndCompanyActivityId(CustomerRequestStatus customerRequestStatus, Long requestTypeId, Long companyActivityId);
}
