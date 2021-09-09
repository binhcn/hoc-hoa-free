package dev.binhcn.controller;

import dev.binhcn.constant.Constant;
import dev.binhcn.dto.CategoryAndTopic;
import dev.binhcn.model.Category;
import dev.binhcn.model.Exercise;
import dev.binhcn.model.ExerciseId;
import dev.binhcn.model.Topic;
import dev.binhcn.repository.CategoryRepository;
import dev.binhcn.repository.ExerciseRepository;
import dev.binhcn.repository.TopicRepository;
import dev.binhcn.util.FileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {

  private final CategoryRepository categoryRepository;
  private final TopicRepository topicRepository;
  private final ExerciseRepository exerciseRepository;

  @GetMapping("/topics")
  public ResponseEntity getCategoryAndTopic() {
    List<Category> categoryList = categoryRepository.findAll();
    List<Topic> topicList = topicRepository.findAll();
    List<CategoryAndTopic> response = new ArrayList<>();

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
      response.add(item);
    }
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/exercises")
  public ResponseEntity saveUser(int topicId, String question,
      @RequestParam("solutionImage") MultipartFile multipartFile) {
    String solutionImageName = multipartFile.getOriginalFilename();
    Exercise exercise = new Exercise();
//    exercise.setExerciseId(new ExerciseId(1, topicId));
    exercise.setId(1);
    exercise.setQuestion(question);
    exercise.setTopicId(topicId);
    exercise.setSolutionImage(solutionImageName);
    exercise.setCreatedAt(System.currentTimeMillis());
    Exercise response = exerciseRepository.save(exercise);

    String fileName = StringUtils.cleanPath(solutionImageName);
    String uploadDir = Constant.IMAGE_DIR + Constant.SEPARATE;
    FileUtil.saveFile(uploadDir, fileName, multipartFile);
    return ResponseEntity.ok(response);
  }
}
