package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Complains;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ComplainSpecifications {

    public static Specification<Complains> buildSpecifications(ComplainsSearchForm searchObject) {
        return (Root<Complains> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getRequestId() != null )
                predicates.add(cb.equal(root.get("requestId"), searchObject.getRequestId()));

            if (searchObject.getRequesterId() != null )
                predicates.add(cb.equal(root.get("requesterId"), searchObject.getRequesterId()));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}