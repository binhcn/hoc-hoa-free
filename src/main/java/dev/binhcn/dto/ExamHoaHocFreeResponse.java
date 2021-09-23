package dev.binhcn.dto;

import lombok.Data;

@Data
public class ExamHoaHocFreeResponse extends HoaHocFreeResponse {
  private long id;
  private String question;
  private int topicId;
  private String questionImage;
  private String examFile;
  private String createdAt;
}
