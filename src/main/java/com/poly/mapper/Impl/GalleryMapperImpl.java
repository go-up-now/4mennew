package com.poly.mapper.Impl;

import com.poly.dto.request.GalleryCreateRequest;
import com.poly.dto.request.GalleryUpdateRequest;
import com.poly.entity.Gallery;
import com.poly.entity.Product;
import com.poly.mapper.GalleryMapper;
import org.springframework.stereotype.Component;

@Component
public class GalleryMapperImpl implements GalleryMapper {

    @Override
    public Gallery toBanner(GalleryCreateRequest request) {
        if (request == null) {
            return null;
        }

        Gallery galery = new Gallery();
        galery.setThumbnail(request.getThumbnail());
        // Đối với Galery entity, chúng ta không lưu trữ productId mà lưu trữ trực tiếp Product
        // Vì vậy, chúng ta cần xây dựng một đối tượng Product từ productId và gán vào Galery
        Product product = new Product();
        product.setId(request.getProductId());
        galery.setProduct(product);

        return galery;
    }

    @Override
    public Gallery toBannerDTO(Gallery galery) {
        // Bạn có thể triển khai logic chuyển đổi từ Galery entity sang DTO ở đây nếu cần thiết
        return null;
    }

    @Override
    public void updateGallery(Gallery galery, GalleryUpdateRequest request) {
        // Update thumbnail và product của Galery từ dữ liệu trong request
        galery.setThumbnail(request.getThumbnail());
        // Đối với product, chúng ta không cần update vì product không thể thay đổi trong quá trình cập nhật
    }
}
