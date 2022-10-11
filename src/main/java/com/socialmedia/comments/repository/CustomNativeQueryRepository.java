package com.socialmedia.comments.repository;

import java.util.List;
import java.util.Optional;

public interface CustomNativeQueryRepository<T> {

  Optional<T> getSingleResult(String sql, String sqlResultSetMappingName);

  List<T> getResults(String sql, String sqlResultSetMappingName);

}
