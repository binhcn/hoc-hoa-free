package dev.binhcn.controller;

import dev.binhcn.model.Category;
import dev.binhcn.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RequiredArgsConstructor
public class Controller {

  private final CategoryRepository categoryRepository;

  @GetMapping("/topics")
  public ResponseEntity getCategoryAndTopic() {
    List<Category> categoryList = categoryRepository.findAll();
    return ResponseEntity.ok("binh");
  }
}
