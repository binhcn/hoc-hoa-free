package dev.binhcn.dto;

import dev.binhcn.model.Topic;
import java.util.List;
import lombok.Data;

@Data
public class CategoryAndTopic {
  private int categoryId;
  private String title;
  private List<Topic> topicList;
}
