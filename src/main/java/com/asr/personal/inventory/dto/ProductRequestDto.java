package com.asr.personal.inventory.dto;

import com.asr.personal.inventory.config.validation.CreateValidation;
import com.asr.personal.inventory.config.validation.UpdateValidation;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

  @NotBlank(groups = { CreateValidation.class })
  String name;

  String brand;

  @NotNull(groups = { CreateValidation.class })
  @DecimalMin(value = "0.00", inclusive = false, groups = {
      CreateValidation.class,
      UpdateValidation.class
  })
  BigDecimal price;
}
