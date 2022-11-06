package com.asr.personal.inventory.service.impl;

import com.asr.personal.inventory.dto.ProductRequestDto;
import com.asr.personal.inventory.dto.ProductResponseDto;
import com.asr.personal.inventory.entity.Product;
import com.asr.personal.inventory.mapper.ProductMapper;
import com.asr.personal.inventory.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

  BaseProductServiceImpl baseProductService;

  ProductMapper productMapper;

  @Override
  public Mono<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
    Product product = productMapper.mapProductFromRequest(productRequestDto);
    return baseProductService
        .createProduct(product)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> getProductById(String productId) {
    return baseProductService
        .getProductById(productId)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> deleteProductById(String productId) {
    return baseProductService
        .deleteProductById(productId)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Mono<ProductResponseDto> updateProductById(
      final String productId, final ProductRequestDto request, final boolean isPatch) {
    return baseProductService
        .updateProductById(
            productId,
            isPatch ? product -> productMapper.patchProduct(product, request)
                    : product -> productMapper.updateProduct(product, request)
        )
        .map(productMapper::mapProductResponseDtoFromProduct);
  }

  @Override
  public Flux<ProductResponseDto> getAllProducts(Pageable pageable) {
    return baseProductService
        .getAllProducts(pageable)
        .map(productMapper::mapProductResponseDtoFromProduct);
  }
}
