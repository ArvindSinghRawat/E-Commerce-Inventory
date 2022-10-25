package com.asr.personal.inventory.controller;

import com.asr.personal.inventory.config.validation.CreateValidation;
import com.asr.personal.inventory.config.validation.UpdateValidation;
import com.asr.personal.inventory.dto.ProductRequestDto;
import com.asr.personal.inventory.dto.ProductResponseDto;
import com.asr.personal.inventory.service.ProductService;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

  ProductService productService;

  @Validated({ CreateValidation.class })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<ProductResponseDto> createProduct(
      @Valid @RequestBody ProductRequestDto request) {
    return productService.createProduct(request);
  }

  @GetMapping("/{productId}")
  public Mono<ResponseEntity<ProductResponseDto>> getProductById(
      @Valid @NotBlank @NotNull @PathVariable String productId) {
    return productService.getProductById(productId)
                         .map(ResponseEntity::ok)
                         .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{productId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<ProductResponseDto> deleteProductById(
      @Valid @NotBlank @NotNull @PathVariable String productId) {
    return productService.deleteProductById(productId);
  }

  @Validated({ UpdateValidation.class })
  @PutMapping("/{productId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<ProductResponseDto> updateProductById(
      @Valid @NotBlank @NotNull @PathVariable String productId,
      @Valid @RequestBody ProductRequestDto requestDto) {
    return productService.updateProductById(productId, requestDto, false);
  }

  @Validated({ UpdateValidation.class })
  @PatchMapping("/{productId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Mono<ProductResponseDto> patchProductById(
      @Valid @NotBlank @NotNull @PathVariable String productId,
      @Valid @RequestBody ProductRequestDto requestDto) {
    return productService.updateProductById(productId, requestDto, true);
  }

}
