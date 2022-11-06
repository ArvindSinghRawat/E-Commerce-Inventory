package com.asr.personal.inventory.graphql.data.fetcher;

import com.asr.personal.inventory.dto.ProductResponseDto;
import com.asr.personal.inventory.service.ProductService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  ProductService productService;

  @DgsQuery
  public Mono<ProductResponseDto> productById(
      @Valid @NotBlank @NotNull @InputArgument String id) {
    return productService.getProductById(id);
  }

  @DgsQuery
  public Flux<ProductResponseDto> products() {
    return productService.getAllProducts(PageRequest.of(0, 1, Sort.by("name")));
  }
}
