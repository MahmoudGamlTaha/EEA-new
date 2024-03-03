package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.ActivitySearchForm;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ActivitySpecifications {

    public static Specification<CompanyActivity> buildSpecifications(ActivitySearchForm searchObject) {
        return (Root<CompanyActivity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getName() != null && !searchObject.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchObject.getName() + "%"));

            if (searchObject.getCode() != null && !searchObject.getCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + searchObject.getCode() + "%"));

            if (searchObject.getDescription() != null && !searchObject.getDescription().isEmpty())
                predicates.add(cb.like(root.get("description"), "%" + searchObject.getDescription() + "%"));

            if (searchObject.getAdministrativeStructureId() != null)
                predicates.add(cb.equal(root.get("administrativeStructureId"), searchObject.getAdministrativeStructureId()));

            if (searchObject.getMainActivityId() != null)
                predicates.add(cb.equal(root.get("mainActivityId"), searchObject.getMainActivityId()));

            if (searchObject.getCountryCode() != null && !searchObject.getCountryCode().isEmpty())
                predicates.add(cb.like(root.get("countryCode"), "%" + searchObject.getCountryCode() + "%"));

            if (searchObject.getConstructurerId() != null)
                predicates.add(cb.equal(root.get("constructurerId"), searchObject.getConstructurerId()));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}