package dev.binhcn.dto;

import lombok.Data;

@Data
public class ExerciseHoaHocFreeResponse extends HoaHocFreeResponse {
  private int id;
  private String question;
  private int topicId;
  private String solutionImage;
  private String createdAt;
}
