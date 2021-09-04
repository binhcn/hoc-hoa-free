package dev.binhcn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("topic")
public class Topic {
  @Id
  private int id;
  private String title;
  private int categoryId;
}
