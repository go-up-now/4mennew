package com.poly.mapper;

import com.poly.dto.request.GalleryCreateRequest;
import com.poly.dto.request.GalleryUpdateRequest;
import com.poly.entity.Gallery;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public interface GalleryMapper {
    Gallery toBanner(GalleryCreateRequest request);
    Gallery toBannerDTO(Gallery galery);
    void updateGallery(@MappingTarget Gallery galery, GalleryUpdateRequest request);
}
