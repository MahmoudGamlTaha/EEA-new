package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.AdministrativeStructureSearchForm;
import com.backend.EEA.model.entity.masterdata.AdministrativeStructure;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AdministrativeStructureSpecifications {

    public static Specification<AdministrativeStructure> buildSpecifications(AdministrativeStructureSearchForm searchObject) {
        return (Root<AdministrativeStructure> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getName() != null && !searchObject.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchObject.getName() + "%"));

            if (searchObject.getParentId() != null)
                predicates.add(cb.equal(root.get("parentId"), searchObject.getParentId()));

            if (searchObject.getParentName() != null && !searchObject.getParentName().isEmpty()) {
                Join<AdministrativeStructure, AdministrativeStructure> parentJoin = root.join("parent");
                predicates.add(cb.like((parentJoin.get("name")), "%" + searchObject.getParentName() + "%"));
            }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
