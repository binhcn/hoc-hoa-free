package dev.binhcn.util;

import dev.binhcn.dto.ExamHoaHocFreeResponse;
import dev.binhcn.model.Exam;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExamUtil {

  public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  public static ExamHoaHocFreeResponse parseDetails(Exam exam) {
    ExamHoaHocFreeResponse details = new ExamHoaHocFreeResponse();
    details.setId(exam.getId());
    details.setQuestion(exam.getTitle());
    details.setTopicId(exam.getTopicId());
    details.setQuestionImage(exam.getQuestionImage());
    details.setExamFile(exam.getExamFile());
    details.setCreatedAt(sdf.format(exam.getCreatedAt()));
    return details;
  }

  public static List<ExamHoaHocFreeResponse> parseDetailsList(List<Exam> examList) {
    List<ExamHoaHocFreeResponse> detailsList = new ArrayList<>();
    for (int i = 0; i < examList.size(); i++) {
      ExamHoaHocFreeResponse details = parseDetails(examList.get(i));
      detailsList.add(details);
    }
    return detailsList;
  }
}
