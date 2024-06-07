package com.poly.service;

import com.poly.dto.request.BannerCreateRequest;
import com.poly.dto.request.BannerUpdateRequest;
import com.poly.dto.request.ProductCreateRequest;
import com.poly.dto.request.ProductUpdateRequest;
import com.poly.entity.Galery;
import com.poly.entity.Product;


import java.util.List;

public interface BannerService {
    List<Galery> getAllBanners();
    Galery getBannerById(Long id);
    Galery createBanner(BannerCreateRequest request);
    Galery  updateBanner(BannerUpdateRequest request, Long id);
    void deleteProduct(Long id);
}
