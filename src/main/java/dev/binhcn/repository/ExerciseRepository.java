package dev.binhcn.repository;

import dev.binhcn.model.Exercise;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

  @Query("SELECT * FROM exercise WHERE topic_id = :topicId ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findByTopicId(
      @Param("topicId") int topicId,
      @Param("limit") int limit,
      @Param("offset") int offset);

  @Query("SELECT * FROM exercise WHERE category_id = :categoryId ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findByCategoryId(
      @Param("categoryId") int topicId,
      @Param("limit") int limit,
      @Param("offset") int offset);

  @Query("SELECT * FROM exercise WHERE question LIKE CONCAT('%',:keyword,'%') ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findByKeyword(
      @Param("keyword") String keyword,
      @Param("limit") int limit,
      @Param("offset") int offset);

  @Query("SELECT * FROM exercise ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findAll(
      @Param("limit") int limit,
      @Param("offset") int offset);

  Exercise findById(long id);

  long countByTopicId(int topicId);

  long countByCategoryId(int categoryId);
}
