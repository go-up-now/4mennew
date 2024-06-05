package com.poly.service.Impl;

import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Brand;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.mapper.ProductMapper;
import com.poly.repository.BrandRepository;
import com.poly.repository.CategoryRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByQuantity() {
        return productRepository.getProductsByQuantity();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product createProduct(ProductCreateRequest request) {
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(request.getBrand()).orElseThrow(() -> new RuntimeException("Brand not found"));

        Product product = productMapper.toProduct(request);
        product.setCategory(category);
        product.setBrand(brand);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, int id) {
        Product product = this.getProductById(id);
        Category category = categoryRepository.findById(request.getCategory()).orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(request.getBrand()).orElseThrow(() -> new RuntimeException("Brand not found"));

        productMapper.toUpdateProduct(product, request);
        product.setCategory(category);
        product.setBrand(brand);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
