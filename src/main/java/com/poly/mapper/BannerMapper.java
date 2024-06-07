package com.poly.mapper;

import com.poly.dto.request.BannerCreateRequest;
import com.poly.dto.request.BannerUpdateRequest;
import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.Galery;
import com.poly.entity.User;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public interface BannerMapper {
    Galery toBanner(BannerCreateRequest request);
    Galery toBannerDTO(Galery galery);
    void updateGallery(@MappingTarget Galery galery, BannerUpdateRequest request);
}
