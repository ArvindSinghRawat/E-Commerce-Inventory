package com.asr.personal.inventory.dto.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationErrorResponse {

  @Builder.Default
  List<Violation> violations = new ArrayList<>();

  @Builder.Default
  LocalDateTime timestamp = LocalDateTime.now();
}
