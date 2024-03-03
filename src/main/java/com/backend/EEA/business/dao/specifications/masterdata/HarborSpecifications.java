package com.backend.EEA.business.dao.specifications.masterdata;


import com.backend.EEA.model.dto.search.HarborSearchForm;
import com.backend.EEA.model.entity.masterdata.Harbor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class HarborSpecifications {

    public static Specification<Harbor> buildSpecifications(HarborSearchForm harborSearchForm) {
        return (Root<Harbor> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (harborSearchForm.getName() != null && !harborSearchForm.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + harborSearchForm.getName() + "%"));


            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}