package com.asr.personal.inventory.service;

import com.asr.personal.inventory.dto.ProductRequestDto;
import com.asr.personal.inventory.dto.ProductResponseDto;
import reactor.core.publisher.Mono;

public interface ProductService {
  Mono<ProductResponseDto> createProduct(ProductRequestDto productRequestDto);

  Mono<ProductResponseDto> getProductById(String productId);

  Mono<ProductResponseDto> deleteProductById(String productId);

  Mono<ProductResponseDto> updateProductById(
      String productId, ProductRequestDto request, boolean isPatch);
}
