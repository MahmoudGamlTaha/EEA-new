package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Task;

public interface TaskRepository extends GenericJPARepository<Task> {

    Task findFirstByIdAndEntityId(Long id, Long entityId);

    Task findFirstByNameAndEntityId(String name, Long entityId);
}
