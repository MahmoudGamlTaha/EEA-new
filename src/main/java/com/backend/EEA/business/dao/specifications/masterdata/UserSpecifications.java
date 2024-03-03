package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.model.entity.masterdata.UserRole;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> buildSpecifications(UserSearchForm searchForm) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchForm.getEntityId()));
            if (searchForm.getUsername() != null && !searchForm.getUsername().isEmpty())
                predicates.add(cb.like(root.get("username"), "%" + searchForm.getUsername() + "%"));

            if (searchForm.getName() != null && !searchForm.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchForm.getName() + "%"));


           // Join<User, UserRole> userWithRole = root.join("userRoles", JoinType.LEFT);
           // predicates.add(cb.notEqual(userWithRole.get("roleId"), 4));
            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
