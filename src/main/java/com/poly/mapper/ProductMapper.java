package com.poly.mapper;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Product;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface ProductMapper {
    Product toProduct(ProductCreateRequest request);
    Product toUpdateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
