package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.MainActivitySearchForm;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MainActivitySpecifications {

    public static Specification<RequestHeader> buildSpecifications(MainActivitySearchForm searchForm) {
        return (Root<RequestHeader> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchForm.getEntityId()));

            if (searchForm.getId() != null)
                predicates.add(cb.equal(root.get("id"), searchForm.getId()));

            if (searchForm.getName() != null && !searchForm.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchForm.getName() + "%"));

            if (searchForm.getCode() != null && !searchForm.getCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + searchForm.getCode() + "%"));



            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
