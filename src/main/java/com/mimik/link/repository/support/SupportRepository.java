package com.mimik.link.repository.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @param <T>
 */
@NoRepositoryBean
public interface SupportRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
