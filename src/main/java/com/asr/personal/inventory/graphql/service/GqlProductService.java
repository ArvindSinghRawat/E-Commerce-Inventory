package com.asr.personal.inventory.graphql.service;

import com.asr.personal.inventory.dto.graphql.types.Product;
import com.asr.personal.inventory.dto.graphql.types.ProductInput;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GqlProductService {
  Mono<Product> createProduct(ProductInput productRequestDto);

  Mono<Product> getProductById(String productId);

  Mono<Product> deleteProductById(String productId);

  Mono<Product> updateProductById(
      String productId, ProductInput request, boolean isPatch);

  Flux<Product> getAllProducts(Pageable pageable);
}
