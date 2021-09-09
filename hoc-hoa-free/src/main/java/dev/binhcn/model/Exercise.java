package dev.binhcn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("exercise")
public class Exercise {
//  @Embedded(onEmpty = OnEmpty.USE_NULL)
//  private ExerciseId exerciseId;
  @Id
  private int id;
  private String question;
//  @Id
  private int topicId;
  private String solutionImage;
  private long createdAt;
}
