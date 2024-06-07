package com.poly.service.Impl;

import com.poly.dto.request.BannerCreateRequest;
import com.poly.dto.request.BannerUpdateRequest;
import com.poly.entity.*;

import com.poly.mapper.BannerMapper;

import com.poly.repository.BannerRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.BannerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerRepository bannerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BannerMapper bannerMapper;
    @Override
    public List<Galery> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
        public Galery getBannerById(Long id) {
            Optional<Galery> optionalGalery = bannerRepository.findById(id);
            return optionalGalery.orElse(null); // Trả về null nếu không tìm thấy đối tượng
            // Hoặc sử dụng optionalGalery.orElseThrow() để ném một ngoại lệ nếu không tìm thấy đối tượng
        }

    @Override
    public Galery createBanner(BannerCreateRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Galery galery = bannerMapper.toBanner(request);
        galery.setProduct(product);

        return bannerRepository.save(galery);
    }

    @Override
    public Galery updateBanner( BannerUpdateRequest request, Long id) {
        Galery galery = this.getBannerById(id);
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Role not found"));
        bannerMapper.updateGallery(galery, request);
        galery.setProduct(product);
        return bannerRepository.save(galery);
    }


    @Override
        public void deleteProduct(Long id) {
            bannerRepository.deleteById(id);
        }

    }

