package com.poly.service;

import com.poly.dto.request.GalleryCreateRequest;
import com.poly.dto.request.GalleryUpdateRequest;
import com.poly.entity.Gallery;


import java.util.List;

public interface GalleryService {
    List<Gallery> getAllBanners();
    Gallery getBannerById(Long id);
    Gallery createBanner(GalleryCreateRequest request);
    Gallery updateBanner(GalleryUpdateRequest request, Long id);
    void deleteProduct(Long id);
}
