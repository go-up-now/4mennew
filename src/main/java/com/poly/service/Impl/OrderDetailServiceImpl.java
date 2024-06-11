package com.poly.service.Impl;

import com.poly.dto.request.OrderDetailCreateRequest;
import com.poly.dto.request.OrderDetailUpdateRequest;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import com.poly.mapper.OrderMapper;
import com.poly.repository.OrderDetailRepository;
import com.poly.service.OrderDetailService;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductService productService;
    @Override
    public OrderDetail getOrder(int id) {
        // Implement your logic here to get order by id from repository
        return null;
    }

    @Override
    public List<OrderDetail> getAllOrder() {
        return orderDetailRepository.findAll();
    }

//    @Override
//    public OrderDettail createOrder(OrderDetailCreateRequest request) {
//        Product product = productService.getProductById(request.getProduct().getId());
//        OrderDettail orderDetail = orderMapper.toOrderDetail(request);
//        orderDetail.setProduct(product);
//        return orderDetailRepository.save(orderDetail);
//    }
@Override
public OrderDetail createOrder(OrderDetailCreateRequest request) {
    if (request == null || request.getProduct() == null) {
        // Kiểm tra xem request hoặc sản phẩm có null không
        throw new IllegalArgumentException("Yêu cầu đặt hàng hoặc sản phẩm không hợp lệ");
    }

    Product product = productService.getProductById(request.getProduct().getId());
    if (product == null) {
        // Kiểm tra xem sản phẩm có tồn tại không
        throw new IllegalArgumentException("Sản phẩm không tồn tại");
    }

    OrderDetail orderDetail = orderMapper.toOrderDetail(request);
    orderDetail.setProduct(product);

    // Nếu cần, quản lý giao dịch ở đây
    // Chẳng hạn: Transactional annotation hoặc sử dụng EntityManager

    return orderDetailRepository.save(orderDetail);
}



    @Override
    public OrderDetail updateOrder(OrderDetailUpdateRequest request, int id) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id).orElse(null);
        if (existingOrderDetail == null) {
            return null;
        }

        OrderDetail updatedOrderDetail = orderMapper.toUpdateOrder(existingOrderDetail, request);

        return orderDetailRepository.save(updatedOrderDetail);
    }

    @Override
    public void deleteOrder(int id) {
        // Implement your logic here to delete order by id from repository
    }

    @Override
    public List<OrderDetail> findOrderDetailsByOrderId(int orderId) {
        return orderDetailRepository.findOrderDetailsByOrderId(orderId);
    }
    @Override
    public List<OrderDetail> findCurrentOrderByUserId(int userId) {
        return orderDetailRepository.findCurrentOrdersByUserId(userId);
    }


}
