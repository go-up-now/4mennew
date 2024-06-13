package com.poly.mapper;

import com.poly.dto.request.GalleryCreateRequest;
import com.poly.dto.request.GalleryUpdateRequest;
import com.poly.entity.Gallery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface GalleryMapper {
    @Mapping(target = "product", ignore = true)
    Gallery toBanner(GalleryCreateRequest request);
//    Gallery toBannerDTO(Gallery galery);

    @Mapping(target = "product", ignore = true)
    void updateGallery(@MappingTarget Gallery galery, GalleryUpdateRequest request);
}
