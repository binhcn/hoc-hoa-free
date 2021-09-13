package dev.binhcn.repository;

import dev.binhcn.model.Exercise;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

  @Query("SELECT * FROM exercise WHERE topic_id = :topicId ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findByTopicId(
      @Param("topicId") int topicId,
      @Param("limit") int limit,
      @Param("offset") int offset);

  @Query("SELECT * FROM exercise ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exercise> findAll(
      @Param("limit") int limit,
      @Param("offset") int offset);

  Exercise findById(long i);

  long countByTopicId(int topicId);
}
