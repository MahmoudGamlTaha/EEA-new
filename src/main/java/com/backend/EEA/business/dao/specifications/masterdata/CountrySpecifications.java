package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.CountrySearchForm;
import com.backend.EEA.model.entity.masterdata.Country;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CountrySpecifications {

    public static Specification<Country> buildSpecifications(CountrySearchForm searchForm) {
        return (Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchForm.getEntityId()));
            if (searchForm.getName() != null && !searchForm.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchForm.getName() + "%"));

            if (searchForm.getCode() != null && !searchForm.getCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + searchForm.getCode() + "%"));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
