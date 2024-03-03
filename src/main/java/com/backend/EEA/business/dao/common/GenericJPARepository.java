package com.backend.EEA.business.dao.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface GenericJPARepository<T extends Serializable> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {

}
