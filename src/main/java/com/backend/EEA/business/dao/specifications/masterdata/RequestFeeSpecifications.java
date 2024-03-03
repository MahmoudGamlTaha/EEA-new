package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.RdfSearchForm;
import com.backend.EEA.model.dto.search.RequestFeeSearchForm;
import com.backend.EEA.model.entity.masterdata.Rdf;
import com.backend.EEA.model.entity.masterdata.RequestFees;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RequestFeeSpecifications {

    public static Specification<RequestFees> buildSpecifications(RequestFeeSearchForm searchObject) {
        return (Root<RequestFees> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getRequesterId() != null)
                predicates.add(cb.equal(root.get("requesterId"), searchObject.getRequesterId()));

            if (searchObject.getRequestId() != null)
                predicates.add(cb.equal(root.get("requestId"), searchObject.getRequestId()));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}