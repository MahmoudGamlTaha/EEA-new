package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;

public interface AdministrativeStructureRepository extends GenericJPARepository<AdministrativeStructure> {

    AdministrativeStructure findFirstByIdAndEntityId(Long id, Long entityId);

    AdministrativeStructure findFirstByParentIdAndEntityId(Long parentId, Long entityId);

    AdministrativeStructure findFirstByNameAndEntityId(String name, Long entityId);
}
