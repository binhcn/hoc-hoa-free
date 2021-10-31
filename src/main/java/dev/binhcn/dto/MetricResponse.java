package dev.binhcn.dto;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Data;

@Data
public class MetricResponse {
  private Map<String, AtomicLong> metrics;
}
