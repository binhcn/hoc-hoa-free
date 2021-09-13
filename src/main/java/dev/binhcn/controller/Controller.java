package dev.binhcn.controller;

import dev.binhcn.dto.ExerciseHoaHocFreeResponse;
import dev.binhcn.dto.ExerciseListResponse;
import dev.binhcn.statics.Constant;
import dev.binhcn.dto.CategoryAndTopic;
import dev.binhcn.dto.CategoryAndTopicResponse;
import dev.binhcn.model.Category;
import dev.binhcn.model.Exercise;
import dev.binhcn.model.Topic;
import dev.binhcn.repository.CategoryRepository;
import dev.binhcn.repository.ExerciseRepository;
import dev.binhcn.repository.TopicRepository;
import dev.binhcn.util.ExerciseUtil;
import dev.binhcn.util.FileUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class Controller {

  private final CategoryRepository categoryRepository;
  private final TopicRepository topicRepository;
  private final ExerciseRepository exerciseRepository;

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
  public ResponseEntity saveExercise(int topicId, String question,
      @RequestParam("solutionImage") MultipartFile multipartFile) {
    String solutionImage = multipartFile.getOriginalFilename();
    Exercise exercise = new Exercise();
    exercise.setQuestion(question);
    exercise.setTopicId(topicId);
    exercise.setSolutionImage(solutionImage);
    exercise.setCreatedAt(System.currentTimeMillis());
    Exercise response = exerciseRepository.save(exercise);

    String fileName = StringUtils.cleanPath(solutionImage);
    FileUtil.saveFile(Constant.IMAGE_DIR, fileName, multipartFile);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/exercises")
  public ResponseEntity getExercises(
      @RequestParam(value = "topicId", required = false) String topicIdString,
      @RequestParam(value = "text", required = false) String text,
      @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    int offset = pageSize * (currentPage - 1);
    List<Exercise> exerciseList;
    long total;
    if (topicIdString.isEmpty()) {
      exerciseList = exerciseRepository.findAll(pageSize, offset);
      total = exerciseRepository.count();
    } else {
      int topicId = Integer.parseInt(topicIdString);
      exerciseList = exerciseRepository.findByTopicId(topicId, pageSize, offset);
      total = exerciseRepository.countByTopicId(topicId);
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

  @GetMapping("/exercises/{id}")
  public ResponseEntity getExercises(@PathVariable("id") long id) {
    Exercise exercise = exerciseRepository.findById(id);
    ExerciseHoaHocFreeResponse response = ExerciseUtil.parseDetails(exercise);
    return ResponseEntity.ok(response);
  }
}
