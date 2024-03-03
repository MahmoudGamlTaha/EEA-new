package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.dto.search.RdfSearchForm;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.Rdf;
import com.backend.EEA.model.entity.masterdata.RequestHeader;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RdfSpecifications {

    public static Specification<Rdf> buildSpecifications(RdfSearchForm searchObject) {
        return (Root<Rdf> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getRequesterId() != null)
                predicates.add(cb.equal(root.get("requesterId"), searchObject.getRequesterId()));

             if(searchObject.getAdministrativeId() != null && searchObject.getAdministrativeId().length > 0){
                 Join<RequestHeader, Rdf> rdfRequest = root.join("requestHeader");
                 predicates.add(rdfRequest.get("administrativeForwardTo").in(searchObject.getAdministrativeId()));
             }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}