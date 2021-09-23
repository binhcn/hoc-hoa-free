package dev.binhcn.controller;

import dev.binhcn.dto.CategoryAndTopic;
import dev.binhcn.dto.CategoryAndTopicResponse;
import dev.binhcn.dto.ExamHoaHocFreeResponse;
import dev.binhcn.dto.ExamListResponse;
import dev.binhcn.dto.ExerciseHoaHocFreeResponse;
import dev.binhcn.dto.ExerciseListResponse;
import dev.binhcn.model.Category;
import dev.binhcn.model.Exam;
import dev.binhcn.model.Exercise;
import dev.binhcn.model.Topic;
import dev.binhcn.repository.CategoryRepository;
import dev.binhcn.repository.ExamRepository;
import dev.binhcn.repository.ExerciseRepository;
import dev.binhcn.repository.TopicRepository;
import dev.binhcn.statics.Constant;
import dev.binhcn.util.ExamUtil;
import dev.binhcn.util.ExerciseUtil;
import dev.binhcn.util.FileUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class Controller {

  private final CategoryRepository categoryRepository;
  private final TopicRepository topicRepository;
  private final ExerciseRepository exerciseRepository;
  private final ExamRepository examRepository;

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

    String solutionImageName = FileUtil.saveFile(solutionImageFile, Constant.IMAGE_DIR);
    exercise.setSolutionImage(solutionImageName);
    if (Objects.nonNull(questionImageFile) && !questionImageFile.isEmpty()) {
      String questionImageName = FileUtil.saveFile(questionImageFile, Constant.IMAGE_DIR);
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
    if (topicIdString.length() > 0) {
      int topicId = Integer.parseInt(topicIdString);
      exerciseList = exerciseRepository.findByTopicId(topicId, pageSize, offset);
      total = exerciseRepository.countByTopicId(topicId);
    } else if(categoryIdString.length() > 0) {
      int categoryId = Integer.parseInt(categoryIdString);
      exerciseList = exerciseRepository.findByCategoryId(categoryId, pageSize, offset);
      total = exerciseRepository.countByCategoryId(categoryId);
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

  @PostMapping("/exams")
  public ResponseEntity saveExam(String topicId, String title,
      @RequestParam(value = "questionImage") MultipartFile questionImageFile,
      @RequestParam("examFile") MultipartFile examFile) {

    Exam exam = new Exam();
    exam.setTitle(title);
    exam.setTopicId(Integer.parseInt(topicId));
    exam.setCreatedAt(System.currentTimeMillis());
    String questionImageName = FileUtil.saveFile(questionImageFile, Constant.IMAGE_DIR);
    exam.setQuestionImage(questionImageName);
    String examFilename = FileUtil.saveFile(examFile, Constant.FILE_DIR);
    exam.setExamFile(examFilename);

    Exam response = examRepository.save(exam);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/exams")
  public ResponseEntity getExams(
      @RequestParam(value = "topicId", required = false) String topicIdString,
      @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    int offset = pageSize * (currentPage - 1);
    List<Exam> examList;
    long total;
    if (topicIdString.length() > 0) {
      int topicId = Integer.parseInt(topicIdString);
      examList = examRepository.findByTopicId(topicId, pageSize, offset);
      total = examRepository.countByTopicId(topicId);
    } else {
      examList = examRepository.findAll(pageSize, offset);
      total = examRepository.count();
    }
    int lastPage = -1;
    if (pageSize > 0) {
      lastPage = (int)Math.ceil((float)total / pageSize);
    }
    ExamListResponse response = new ExamListResponse();
    response.setTotal(total);
    response.setCurrentPage(currentPage);
    response.setLastPage(lastPage);
    response.setPageSize(pageSize);
    response.setExamList(ExamUtil.parseDetailsList(examList));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/exams/{id}")
  public ResponseEntity deleteExam(@PathVariable("id") long id) {
    examRepository.deleteById(id);
    return ResponseEntity.ok("Delete success");
  }

  @GetMapping("/exams/{id}")
  public ResponseEntity getExam(@PathVariable("id") long id) {
    Exam exam = examRepository.findById(id);
    ExamHoaHocFreeResponse response = ExamUtil.parseDetails(exam);
    return ResponseEntity.ok(response);
  }
}
