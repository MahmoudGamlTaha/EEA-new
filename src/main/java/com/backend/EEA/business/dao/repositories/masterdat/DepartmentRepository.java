package com.backend.EEA.business.dao.repositories.masterdat;

import com.backend.EEA.business.dao.common.GenericJPARepository;
import com.backend.EEA.model.entity.masterdata.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends GenericJPARepository<Department> {

}
