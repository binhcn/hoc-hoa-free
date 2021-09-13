package dev.binhcn.util;

import dev.binhcn.dto.ExerciseHoaHocFreeResponse;
import dev.binhcn.model.Exercise;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExerciseUtil {

  public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  public static ExerciseHoaHocFreeResponse parseDetails(Exercise exercise) {
    ExerciseHoaHocFreeResponse details = new ExerciseHoaHocFreeResponse();
    details.setId(exercise.getId());
    details.setQuestion(exercise.getQuestion());
    details.setTopicId(exercise.getTopicId());
    details.setSolutionImage(exercise.getSolutionImage());
    details.setCreatedAt(sdf.format(exercise.getCreatedAt()));
    return details;
  }

  public static List<ExerciseHoaHocFreeResponse> parseDetailsList(List<Exercise> exerciseList) {
    List<ExerciseHoaHocFreeResponse> detailsList = new ArrayList<>();
    for (int i = 0; i < exerciseList.size(); i++) {
      ExerciseHoaHocFreeResponse details = parseDetails(exerciseList.get(i));
      detailsList.add(details);
    }
    return detailsList;
  }
}
