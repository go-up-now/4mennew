package com.poly.mapper;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toProduct(ProductCreateRequest request);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toUpdateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
