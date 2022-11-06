package com.asr.personal.inventory.graphql.mapper;

import com.asr.personal.inventory.dto.graphql.types.ProductInput;
import com.asr.personal.inventory.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface GqlProductMapper {

  @Mapping(target = "id", ignore = true)
  Product mapProductFromInput(ProductInput source);

  Product mapProductFromResponse(com.asr.personal.inventory.dto.graphql.types.Product source);

  @InheritInverseConfiguration(name = "mapProductFromInput")
  ProductInput mapProductInputFromProduct(Product source);

  @InheritInverseConfiguration(name = "mapProductFromResponse")
  com.asr.personal.inventory.dto.graphql.types.Product mapProductResponseFromProduct(Product source);

  @Mapping(target = "id", ignore = true)
  void updateProduct(@MappingTarget Product target, ProductInput source);

  @Mapping(target = "id", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void patchProduct(@MappingTarget Product target, ProductInput source);
}
