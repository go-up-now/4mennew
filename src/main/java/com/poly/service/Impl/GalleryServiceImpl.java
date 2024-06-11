package com.poly.service.Impl;

import com.poly.dto.request.GalleryCreateRequest;
import com.poly.dto.request.GalleryUpdateRequest;
import com.poly.entity.*;


import com.poly.mapper.GalleryMapper;
import com.poly.repository.GalleryRepository;
import com.poly.repository.ProductRepository;
import com.poly.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    GalleryRepository bannerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GalleryMapper galleryMapper;
    @Override
    public List<Gallery> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
        public Gallery getBannerById(Long id) {
            Optional<Gallery> optionalGalery = bannerRepository.findById(id);
            return optionalGalery.orElse(null); // Trả về null nếu không tìm thấy đối tượng
            // Hoặc sử dụng optionalGalery.orElseThrow() để ném một ngoại lệ nếu không tìm thấy đối tượng
        }

    @Override
    public Gallery createBanner(GalleryCreateRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Gallery galery = galleryMapper.toBanner(request);
        galery.setProduct(product);

        return bannerRepository.save(galery);
    }

    @Override
    public Gallery updateBanner(GalleryUpdateRequest request, Long id) {
        Gallery galery = this.getBannerById(id);
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Role not found"));
        galleryMapper.updateGallery(galery, request);
        galery.setProduct(product);
        return bannerRepository.save(galery);
    }


    @Override
        public void deleteProduct(Long id) {
            bannerRepository.deleteById(id);
        }

    }

