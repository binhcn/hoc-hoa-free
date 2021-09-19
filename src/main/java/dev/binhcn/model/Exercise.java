package dev.binhcn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("exercise")
public class Exercise {
  @Id
  private long id;
  private String question;
  private int topicId;
  private int categoryId;
  private String questionImage;
  private String solutionImage;
  private long createdAt;
}
