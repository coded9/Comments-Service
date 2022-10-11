package com.socialmedia.comments.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CustomNativeQueryRepositoryImpl<T> implements CustomNativeQueryRepository<T> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<T> getSingleResult(String sql, String sqlResultSetMappingName) {
    Query nativeQuery = entityManager.createNativeQuery(sql, sqlResultSetMappingName);
    try {
      T t = (T) nativeQuery.getSingleResult();
      return Optional.ofNullable(t);
    } catch (NoResultException e) {
      log.error(e.getMessage(), e);
      return Optional.empty();
    }
  }

  @Override
  public List<T> getResults(String sql, String sqlResultSetMappingName) {
    Query nativeQuery = entityManager.createNativeQuery(sql, sqlResultSetMappingName);
    try {
      return nativeQuery.getResultList();
    } catch (NoResultException e) {
      log.error(e.getMessage(), e);
      return Collections.emptyList();
    }
  }

}

