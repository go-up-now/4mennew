package com.poly.repository;

import com.poly.entity.Order;
import com.poly.report.ReportRevenueByCategory;
import com.poly.entity.OrderDetail;
import com.poly.report.ReportRevenueByDay;
import com.poly.report.ReportRevenueByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByDay(od.order.createdAt, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDetail od GROUP BY od.order.createdAt")
    List<ReportRevenueByDay> getRevenueByDay();

    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByUser(od.order.user, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDetail od GROUP BY od.order.user")
    List<ReportRevenueByUser> getRevenueByUser();

    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByCategory(od.product.category, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDetail od GROUP BY od.product.category")
    List<ReportRevenueByCategory> getRevenueByCategories();

    @Query("SELECT o FROM Order o WHERE o.user = :userId ")
    List<OrderDetail> findCurrentOrdersByUserId(@Param("userId") int userId);

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId")
    List<OrderDetail> findOrderDetailsByOrderId(@Param("orderId") int orderId);

    void deleteByOrder(Order order);
}
