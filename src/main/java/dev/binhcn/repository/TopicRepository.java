package dev.binhcn.repository;

import dev.binhcn.model.Topic;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
  List<Topic> findAll();
}
