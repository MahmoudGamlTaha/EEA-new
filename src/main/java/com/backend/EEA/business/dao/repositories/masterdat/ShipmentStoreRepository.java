package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Role;
import com.backend.EEA.model.entity.masterdata.ShipmentStore;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentStoreRepository extends GenericJPARepository<ShipmentStore> {
     public ShipmentStore findByCode(String code);
}
