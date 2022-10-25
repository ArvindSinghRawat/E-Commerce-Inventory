package com.asr.personal.inventory.service.impl;

import com.asr.personal.inventory.dto.ProductRequestDto;
import com.asr.personal.inventory.dto.ProductResponseDto;
import com.asr.personal.inventory.entity.Product;
import com.asr.personal.inventory.mapper.ProductMapper;
import com.asr.personal.inventory.repository.ProductRepository;
import com.asr.personal.inventory.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

  ProductRepository productRepository;

  ProductMapper productMapper;

  @Override
  public Mono<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
    Product product = productMapper.mapProductFromRequest(productRequestDto);
    return productRepository
        .save(product)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> getProductById(String productId) {
    return productRepository
        .findById(productId)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> deleteProductById(String productId) {
    return productRepository
        .findById(productId)
        .doOnSuccess(productRepository::delete)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> updateProductById(
      final String productId, final ProductRequestDto request, final boolean isPatch) {
    return productRepository
        .findById(productId)
        .doOnSuccess(product -> {
          if (isPatch) {
            productMapper.patchProduct(product, request);
          } else {
            productMapper.updateProduct(product, request);
          }
        })
        .flatMap(productRepository::save)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }
}
