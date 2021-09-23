package dev.binhcn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("exam")
public class Exam {
  @Id
  private long id;
  private String title;
  private int topicId;
  private String examImage;
  private String examFile;
  private long createdAt;
}
