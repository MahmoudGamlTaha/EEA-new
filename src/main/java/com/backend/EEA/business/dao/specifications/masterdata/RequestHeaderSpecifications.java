package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.ComplainsSearchForm;
import com.backend.EEA.model.dto.search.RequestHeaderSearchForm;
import com.backend.EEA.model.entity.masterdata.Complains;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RequestHeaderSpecifications {

    public static Specification<RequestHeader> buildSpecifications(RequestHeaderSearchForm searchObject) {
        return (Root<RequestHeader> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if(searchObject.getRequesterId() != null)
                predicates.add(cb.equal(root.get("requester"), searchObject.getRequesterId()));
             if(searchObject.getAdministrativeId() != null && searchObject.getAdministrativeId().length > 0){
                 predicates.add(root.get("administrativeForwardTo").in(searchObject.getAdministrativeId()));
             }
             if(searchObject.getRequestId() != null){
                 predicates.add(cb.equal(root.get("id"), searchObject.getRequestId()));
             }
             if(searchObject.getFromDate() != null){
                 predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), searchObject.getFromDate()));
             }
             if(searchObject.getToDate() != null){
                 predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), searchObject.getToDate()));

             }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}