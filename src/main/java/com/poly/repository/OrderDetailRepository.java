package com.poly.repository;

import com.poly.report.ReportRevenueByCategory;
import com.poly.entity.OrderDettail;
import com.poly.report.ReportRevenueByDay;
import com.poly.report.ReportRevenueByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDettail, Integer> {
    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByDay(od.order.createdAt, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDettail od GROUP BY od.order.createdAt")
    List<ReportRevenueByDay> getRevenueByDay();

    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByUser(od.order.user, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDettail od GROUP BY od.order.user")
    List<ReportRevenueByUser> getRevenueByUser();

    @Query("SELECT " +
            "new com.poly.report.ReportRevenueByCategory(od.product.category, sum(od.price * od.num), sum(od.num)) " +
            "FROM OrderDettail od GROUP BY od.product.category")
    List<ReportRevenueByCategory> getRevenueByCategories();
}
