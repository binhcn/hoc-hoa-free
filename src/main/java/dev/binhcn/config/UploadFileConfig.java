package dev.binhcn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "upload-file")
public class UploadFileConfig {
  private boolean usingS3;
  private String endpointUrl;
  private String accessKey;
  private String secretKey;
  private String bucketName;
}
