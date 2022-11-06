package com.asr.personal.inventory.entity;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  String id;

  @NotBlank
  @NotNull
  String name;

  String brand;

  @NotNull
  @DecimalMin(value = "0.00", inclusive = false)
  BigDecimal price;
}
