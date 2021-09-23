package dev.binhcn.repository;

import dev.binhcn.model.Exam;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {

  @Query("SELECT * FROM exam WHERE topic_id = :topicId ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exam> findByTopicId(
      @Param("topicId") int topicId,
      @Param("limit") int limit,
      @Param("offset") int offset);

  @Query("SELECT * FROM exam ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
  List<Exam> findAll(
      @Param("limit") int limit,
      @Param("offset") int offset);

  Exam findById(long id);

  long countByTopicId(int topicId);
}
