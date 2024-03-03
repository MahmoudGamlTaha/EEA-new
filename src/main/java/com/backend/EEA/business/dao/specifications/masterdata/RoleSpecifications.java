package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.masterdata.RoleDto;
import com.backend.EEA.model.dto.search.RoleSearchForm;
import com.backend.EEA.model.entity.masterdata.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RoleSpecifications {

    public static Specification<Role> buildSpecifications(RoleSearchForm roleDto) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
