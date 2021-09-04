package dev.binhcn.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("category")
public class Category {
  @Id
  private int id;
  private String title;
}
