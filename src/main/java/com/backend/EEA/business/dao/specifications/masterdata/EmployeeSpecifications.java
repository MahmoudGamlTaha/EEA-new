package com.backend.EEA.business.dao.specifications.masterdata;

import com.backend.EEA.model.dto.search.EmployeeSearchForm;
import com.backend.EEA.model.entity.masterdata.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecifications {

    public static Specification<Employee> buildSpecifications(EmployeeSearchForm searchForm) {
        return (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), searchForm.getEntityId()));
            if (searchForm.getEmployeeName() != null && !searchForm.getEmployeeName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + searchForm.getEmployeeName() + "%"));

            if (searchForm.getEmployeeCode() != null && !searchForm.getEmployeeCode().isEmpty())
                predicates.add(cb.like(root.get("code"), "%" + searchForm.getEmployeeCode() + "%"));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Employee> buildSpecifications(Employee employee) {
        return (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("entityId"), employee.getEntityId()));

            if (employee.getId() != null)
                predicates.add(cb.notEqual(root.get("id"), employee.getId()));

            if (employee.getName() != null && !employee.getName().isEmpty())
                predicates.add(cb.equal(root.get("name"), employee.getName()));

            if (employee.getCode() != null && !employee.getCode().isEmpty())
                predicates.add(cb.equal(root.get("code"), employee.getCode()));

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
