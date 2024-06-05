package com.poly.service;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByQuantity();
    Product getProductById(int id);
    Product createProduct(ProductCreateRequest request);
    Product updateProduct(ProductUpdateRequest request, int id);
    void deleteProduct(int id);
}
