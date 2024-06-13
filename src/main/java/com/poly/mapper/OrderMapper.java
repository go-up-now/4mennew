package com.poly.mapper;

import com.poly.dto.request.OrderDetailCreateRequest;
import com.poly.dto.request.OrderDetailUpdateRequest;
import com.poly.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDetail toOrderDetail(OrderDetailCreateRequest request);
    OrderDetail toUpdateOrder(@MappingTarget OrderDetail orderDettail, OrderDetailUpdateRequest request);
}
