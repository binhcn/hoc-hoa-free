package dev.binhcn.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryAndTopicResponse extends HoaHocFreeResponse {
  private List<CategoryAndTopic> structure;
}
