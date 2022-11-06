package com.asr.personal.inventory.graphql.service.impl;

import com.asr.personal.inventory.dto.graphql.types.Product;
import com.asr.personal.inventory.dto.graphql.types.ProductInput;
import com.asr.personal.inventory.graphql.mapper.GqlProductMapper;
import com.asr.personal.inventory.graphql.service.GqlProductService;
import com.asr.personal.inventory.service.impl.BaseProductServiceImpl;
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
public class GqlProductServiceImpl implements GqlProductService {

  BaseProductServiceImpl baseProductService;

  GqlProductMapper productMapper;

  @Override
  public Mono<Product> createProduct(ProductInput productRequestDto) {
    com.asr.personal.inventory.entity.Product product = productMapper.mapProductFromInput(productRequestDto);
    return baseProductService
        .createProduct(product)
        .map(productMapper::mapProductResponseFromProduct);
  }

  @Override
  public Mono<Product> getProductById(String productId) {
    return baseProductService
        .getProductById(productId)
        .map(productMapper::mapProductResponseFromProduct);
  }

  @Override
  public Mono<Product> deleteProductById(String productId) {
    return baseProductService
        .deleteProductById(productId)
        .map(productMapper::mapProductResponseFromProduct);
  }

  @Override
  public Mono<Product> updateProductById(String productId, ProductInput request, boolean isPatch) {
    return baseProductService
        .updateProductById(
            productId,
            isPatch ? product -> productMapper.patchProduct(product, request)
                    : product -> productMapper.updateProduct(product, request)
        )
        .map(productMapper::mapProductResponseFromProduct);
  }

  @Override
  public Flux<Product> getAllProducts(Pageable pageable) {
    return baseProductService
        .getAllProducts(pageable)
        .map(productMapper::mapProductResponseFromProduct);
  }
}
