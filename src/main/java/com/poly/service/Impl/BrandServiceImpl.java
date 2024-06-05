package com.poly.service.Impl;

import com.poly.entity.Brand;
import com.poly.repository.BrandRepository;
import com.poly.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(int id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
    }
}
