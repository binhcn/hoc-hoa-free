package dev.binhcn.dto;

import java.util.List;
import lombok.Data;

@Data
public class ExamListResponse extends HoaHocFreeResponse {
  private long total;
  private int currentPage;
  private int lastPage;
  private int pageSize;
  private List<ExamHoaHocFreeResponse> examList;
}
