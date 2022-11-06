package com.asr.personal.inventory.graphql.data.fetcher;

import com.asr.personal.inventory.dto.graphql.types.Product;
import com.asr.personal.inventory.dto.graphql.types.ProductInput;
import com.asr.personal.inventory.graphql.service.GqlProductService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DgsComponent
@Validated
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductDataFetcher {

  GqlProductService productService;

  @DgsQuery
  public Mono<Product> productById(@InputArgument String id) {
    return productService.getProductById(id);
  }

  @DgsQuery
  public Flux<Product> products() {
    return productService.getAllProducts(PageRequest.of(0, 1, Sort.by("name")));
  }

  @DgsMutation
  Mono<Product> addProduct(@InputArgument ProductInput product) {
    return productService.createProduct(product);
  }

  @DgsMutation
  Mono<Product> patchProduct(@InputArgument ProductInput product, @InputArgument String id) {
    return productService.updateProductById(id, product, true);
  }

  @DgsMutation
  Mono<Product> updateProduct(@InputArgument ProductInput product, @InputArgument String id) {
    return productService.updateProductById(id, product, false);
  }

  @DgsMutation
  Mono<Product> deleteProduct(@InputArgument String id) {
    return productService.deleteProductById(id);
  }
}
