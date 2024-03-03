package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.business.dao.repositories.masterdat.RoleRepository;
import com.backend.EEA.model.dto.search.UserSearchForm;
import com.backend.EEA.model.entity.masterdata.User;
import com.backend.EEA.model.entity.masterdata.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
@Component()
@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerSpecifications {
         @Autowired
         RoleRepository roleRepository;
    public static Specification<User> buildSpecifications(UserSearchForm searchForm) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchForm.getEntityId()));
            if (searchForm.getUsername() != null && !searchForm.getUsername().isEmpty())
                predicates.add(cb.like(root.get("username"), "%" + searchForm.getUsername() + "%"));

            if (searchForm.getName() != null && !searchForm.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchForm.getName() + "%"));

            Join<User, UserRole> userWithRole = root.join("userRoles");
            predicates.add(cb.equal(userWithRole.get("roleId"), 4));
            query.distinct(true);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
