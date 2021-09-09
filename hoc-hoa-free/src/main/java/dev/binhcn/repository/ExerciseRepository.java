package dev.binhcn.repository;

import dev.binhcn.model.Exercise;
import dev.binhcn.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
}
