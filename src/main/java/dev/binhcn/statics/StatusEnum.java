package dev.binhcn.statics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
  SUCCESS(1, "Handle request successfully"),
  SOLUTION_IMAGE_NOT_FOUND(-1, "Solution image not found");

  private final int code;
  private final String message;
}
