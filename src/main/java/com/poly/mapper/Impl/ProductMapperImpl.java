package com.poly.mapper.Impl;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Product;
import com.poly.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toProduct(ProductCreateRequest request) {
        if(request == null)
            return null;

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setQuantityInStock(request.getQuantityInStock());
        product.setDescription(request.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    @Override
    public Product toUpdateProduct(Product product, ProductUpdateRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setQuantityInStock(request.getQuantityInStock());
        product.setDescription(request.getDescription());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}
