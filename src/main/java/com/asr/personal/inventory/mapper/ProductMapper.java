package com.asr.personal.inventory.mapper;

import com.asr.personal.inventory.dto.ProductRequestDto;
import com.asr.personal.inventory.dto.ProductResponseDto;
import com.asr.personal.inventory.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  Product mapProductFromRequest(ProductRequestDto source);

  Product mapProductFromResponse(ProductResponseDto source);

  @InheritInverseConfiguration(name = "mapProductFromRequest")
  ProductRequestDto mapProductRequestDtoFromProduct(Product source);

  @InheritInverseConfiguration(name = "mapProductFromResponse")
  ProductResponseDto mapProductResponseDtoFromProduct(Product source);

  @Mapping(target = "id", ignore = true)
  void updateProduct(@MappingTarget Product target, ProductRequestDto source);

  @Mapping(target = "id", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void patchProduct(@MappingTarget Product target, ProductRequestDto source);
}
