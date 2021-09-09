package dev.binhcn.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseId implements Serializable {
  private int id;
  private int topicId;
}
