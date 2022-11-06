package com.asr.personal.inventory.service.impl;

import com.asr.personal.inventory.entity.Product;
import com.asr.personal.inventory.repository.ProductRepository;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BaseProductServiceImpl {

  ProductRepository productRepository;

  public Mono<Product> createProduct(Product product) {
    return productRepository.save(product);
  }

  public Mono<Product> getProductById(String productId) {
    return productRepository.findById(productId);
  }

  public Mono<Product> deleteProductById(String productId) {
    return productRepository
        .findById(productId)
        .doOnSuccess(productRepository::delete);
  }

  /**
   * @param productId   Id of the product which needs to be updated
   * @param adderMethod Mapper which populates the details in the found object, ignored if provided null
   * @return Update value of the product
   */
  public Mono<Product> updateProductById(
      @NonNull final String productId, @Nullable final Consumer<Product> adderMethod) {
    return productRepository
        .findById(productId)
        .doOnSuccess(product -> {
          if (!ObjectUtils.isEmpty(adderMethod)) {
            adderMethod.accept(product);
          }
        })
        .flatMap(productRepository::save);
  }

  public Flux<Product> getAllProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }
}
