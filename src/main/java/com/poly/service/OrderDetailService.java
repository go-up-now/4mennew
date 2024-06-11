package com.poly.service;

import com.poly.dto.request.OrderDetailCreateRequest;
import com.poly.dto.request.OrderDetailUpdateRequest;
import com.poly.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail getOrder(int id);
    List<OrderDetail> getAllOrder();
    OrderDetail createOrder(OrderDetailCreateRequest request);
    OrderDetail updateOrder(OrderDetailUpdateRequest request, int id);
    void deleteOrder(int id);
    List<OrderDetail> findOrderDetailsByOrderId(int orderId);
    List<OrderDetail> findCurrentOrderByUserId(int userId);
}
