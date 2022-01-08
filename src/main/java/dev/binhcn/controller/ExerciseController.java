package dev.binhcn.controller;

import dev.binhcn.config.UploadFileConfig;
import dev.binhcn.dto.CategoryAndTopic;
import dev.binhcn.dto.CategoryAndTopicResponse;
import dev.binhcn.dto.ExerciseHoaHocFreeResponse;
import dev.binhcn.dto.ExerciseListResponse;
import dev.binhcn.dto.MetricResponse;
import dev.binhcn.model.Category;
import dev.binhcn.model.Exercise;
import dev.binhcn.model.Topic;
import dev.binhcn.repository.CategoryRepository;
import dev.binhcn.repository.ExerciseRepository;
import dev.binhcn.repository.TopicRepository;
import dev.binhcn.service.AmazonClient;
import dev.binhcn.statics.Constant;
import dev.binhcn.util.ExerciseUtil;
import dev.binhcn.util.FileUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExerciseController {

  private final CategoryRepository categoryRepository;
  private final TopicRepository topicRepository;
  private final ExerciseRepository exerciseRepository;
  private final AmazonClient amazonClient;
  private final UploadFileConfig uploadFileConfig;
  private final Map<String, AtomicLong> metricMap = new HashMap<>();

  @GetMapping("/structure")
  public ResponseEntity getCategoryAndTopic() {
    List<Category> categoryList = categoryRepository.findAll();
    List<Topic> topicList = topicRepository.findAll();
    List<CategoryAndTopic> categoryAndTopicList = new ArrayList<>();

    for (int i = 0; i < categoryList.size(); i++) {
      List<Topic> selectedTopicList = new ArrayList<>();
      for (int j = 0; j < topicList.size(); j++) {
        Topic topic = topicList.get(j);
        if (topic.getCategoryId() == categoryList.get(i).getId()) {
          selectedTopicList.add(topic);
        }
      }
      CategoryAndTopic item = new CategoryAndTopic();
      item.setCategoryId(categoryList.get(i).getId());
      item.setTitle(categoryList.get(i).getTitle());
      item.setTopicList(selectedTopicList);
      categoryAndTopicList.add(item);
    }
    CategoryAndTopicResponse response = new CategoryAndTopicResponse(categoryAndTopicList);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
    Date date = new Date();
    String key = formatter.format(date);
    AtomicLong value = metricMap.get(key);
    if (value == null) {
      value = new AtomicLong(1);
    } else {
      value.getAndIncrement();
    }
    metricMap.put(key, value);
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/exercises")
  public ResponseEntity saveExercise(String topicId, String categoryId, String question,
      @RequestParam(value = "questionImage", required = false) MultipartFile questionImageFile,
      @RequestParam("solutionImage") MultipartFile solutionImageFile) {

    Exercise exercise = new Exercise();
    exercise.setQuestion(question);
    exercise.setTopicId(Integer.parseInt(topicId));
    exercise.setCategoryId(Integer.parseInt(categoryId));
    exercise.setCreatedAt(System.currentTimeMillis());

    String postfixSolution = FileUtil.getFileExt(solutionImageFile);
    String prefix = categoryId + "-" + topicId + "-" + exercise.getCreatedAt();
    String solutionImageName = prefix + "-solution" + postfixSolution;
    if (uploadFileConfig.isUsingS3()) {
      amazonClient.uploadFile(solutionImageFile, solutionImageName);
    } else {
      FileUtil.saveFile(solutionImageFile, Constant.IMAGE_DIR);
    }
    exercise.setSolutionImage(solutionImageName);

    if (Objects.nonNull(questionImageFile) && !questionImageFile.isEmpty()) {
      String postfixQuestion = FileUtil.getFileExt(questionImageFile);
      String questionImageName = prefix + "-question" + postfixQuestion;
      if (uploadFileConfig.isUsingS3()) {
        amazonClient.uploadFile(questionImageFile, questionImageName);
      } else {
        FileUtil.saveFile(questionImageFile, Constant.IMAGE_DIR);
      }
      exercise.setQuestionImage(questionImageName);
    }
    Exercise response = exerciseRepository.save(exercise);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/exercises")
  public ResponseEntity getExercises(
      @RequestParam(value = "topicId", required = false) String topicIdString,
      @RequestParam(value = "categoryId", required = false) String categoryIdString,
      @RequestParam(value = "text", required = false) String text,
      @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    int offset = pageSize * (currentPage - 1);
    List<Exercise> exerciseList;
    long total;
    if (topicIdString != null && topicIdString.length() > 0) {
      int topicId = Integer.parseInt(topicIdString);
      exerciseList = exerciseRepository.findByTopicId(topicId, pageSize, offset);
      total = exerciseRepository.countByTopicId(topicId);
    } else if(categoryIdString != null && categoryIdString.length() > 0) {
      int categoryId = Integer.parseInt(categoryIdString);
      exerciseList = exerciseRepository.findByCategoryId(categoryId, pageSize, offset);
      total = exerciseRepository.countByCategoryId(categoryId);
    } else if(text.length() > 0) {
      exerciseList = exerciseRepository.findByKeyword(text, pageSize, offset);
      total = exerciseRepository.countByKeyword(text);
    } else {
      exerciseList = exerciseRepository.findAll(pageSize, offset);
      total = exerciseRepository.count();
    }
    int lastPage = -1;
    if (pageSize > 0) {
      lastPage = (int)Math.ceil((float)total / pageSize);
    }
    ExerciseListResponse response = new ExerciseListResponse();
    response.setTotal(total);
    response.setCurrentPage(currentPage);
    response.setLastPage(lastPage);
    response.setPageSize(pageSize);
    response.setExerciseList(ExerciseUtil.parseDetailsList(exerciseList));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/exercises/{id}")
  public ResponseEntity deleteExercise(@PathVariable("id") long id) {
    exerciseRepository.deleteById(id);
    return ResponseEntity.ok("Delete success");
  }

  @GetMapping("/exercises/{id}")
  public ResponseEntity getExercise(@PathVariable("id") long id) {
    Exercise exercise = exerciseRepository.findById(id);
    ExerciseHoaHocFreeResponse response = ExerciseUtil.parseDetails(exercise);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/metrics")
  public ResponseEntity collectMetrics() {
    MetricResponse response = new MetricResponse();
    response.setMetrics(metricMap);
    return ResponseEntity.ok(response);
  }
}
