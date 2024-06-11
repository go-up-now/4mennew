package com.poly.mapper.Impl;


import com.poly.dto.request.OrderDetailCreateRequest;
import com.poly.dto.request.OrderDetailUpdateRequest;
import com.poly.entity.OrderDetail;
import com.poly.mapper.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDetail toOrderDetail(OrderDetailCreateRequest request) {
        if (request == null) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(request.getId());
        orderDetail.setPrice(request.getPrice());
        orderDetail.setDiscount(request.getDiscount());
        orderDetail.setNum(request.getNum());
        orderDetail.setTotalPrice(request.getTotalPrice());
        orderDetail.setProduct(request.getProduct());

        return orderDetail;
    }

    @Override
    public OrderDetail toUpdateOrder(OrderDetail orderDettail, OrderDetailUpdateRequest request) {
        if (orderDettail == null || request == null) {
            return null;
        }

        orderDettail.setPrice(request.getPrice());
        orderDettail.setDiscount(request.getDiscount());
        orderDettail.setNum(request.getNum());
        orderDettail.setTotalPrice(request.getTotalPrice());
        // Không cập nhật product ở đây vì chỉ cập nhật thông tin khác

        return orderDettail;
    }
}
