package dev.binhcn.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FileUtil {
  public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath)) {
      try {
        Files.createDirectories(uploadPath);
      } catch (IOException e) {
        log.error("Could not create image directory", e);
      }
    }
    try (InputStream inputStream = multipartFile.getInputStream()) {
      Path filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
      log.error("Could not save image file: " + fileName, ioe);
    }
  }

  public static String saveFile(MultipartFile multipartFile, String parentFolder) {
    String imageName = multipartFile.getOriginalFilename();
    String fileName = StringUtils.cleanPath(imageName);
    FileUtil.saveFile(parentFolder, fileName, multipartFile);
    return fileName;
  }
}
