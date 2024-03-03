package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.AdministrativeStructureSearchForm;
import com.backend.EEA.model.dto.search.TaskSearchForm;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import com.backend.EEA.model.entity.masterdata.Task;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStructureSpecifications {

    public static Specification<Task> buildSpecifications(TaskSearchForm searchObject) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getName() != null && !searchObject.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchObject.getName() + "%"));

            if (searchObject.getParentId() != null)
                predicates.add(cb.equal(root.get("parentId"), searchObject.getParentId()));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
