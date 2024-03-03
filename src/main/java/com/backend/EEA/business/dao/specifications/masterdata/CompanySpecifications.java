package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.ActivitySearchForm;
import com.backend.EEA.model.dto.search.CompanySearchForm;
import com.backend.EEA.model.entity.masterdata.Company;
import com.backend.EEA.model.entity.masterdata.CompanyActivity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CompanySpecifications {

    public static Specification<Company> buildSpecifications(CompanySearchForm searchObject) {
        return (Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchObject.getEntityId()));

            if (searchObject.getName() != null && !searchObject.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchObject.getName() + "%"));

            if (searchObject.getCode() != null && !searchObject.getCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + searchObject.getCode() + "%"));

           if(searchObject.getActivityId() != null){
               predicates.add(cb.equal(root.get("activity_id"),searchObject.getActivityId()));
           }
            if(searchObject.getOwnerId() != null){
                predicates.add(cb.equal(root.get("ownerId"), searchObject.getOwnerId()));
            }


            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}