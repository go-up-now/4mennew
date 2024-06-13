package com.poly.service.Impl;

import com.poly.entity.OrderDetail;
import com.poly.repository.OrderDetailRepository;
import com.poly.repository.ProductRepository;
import com.poly.repository.UserRepository;
import com.poly.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashbordServiceImpl implements DashboardService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public double getTotalRevenue() {
        List<OrderDetail> orders = orderDetailRepository.findAll();
        double totalRevenue = 0;

        for (OrderDetail order : orders) {
            System.out.println("Order ID: " + order.getId() + ", Total Amount: " + order.getTotalPrice());
            totalRevenue += order.getTotalPrice();
        }

        System.out.println("Total Revenue: " + totalRevenue);
        return totalRevenue;
    }

    @Override
    public long getTotalProducts() {
        return productRepository.count();
    }

    @Override
    public Map<String, Object> getChartData() {
        List<OrderDetail> orders = orderDetailRepository.findAll();
        Map<YearMonth, Double> revenueByMonth = new HashMap<>();

        // Tạo ra một danh sách của tất cả các tháng trong năm
        List<YearMonth> allMonths = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        Year currentYear = Year.now();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear.getValue(), month);
            allMonths.add(yearMonth);
        }

        // Khởi tạo giá trị doanh thu ban đầu cho tất cả các tháng
        for (YearMonth month : allMonths) {
            revenueByMonth.put(month, 0.0);
        }

        // Tính tổng doanh thu thực tế từ các đơn hàng
        for (OrderDetail order : orders) {
            YearMonth month = YearMonth.from(order.getDateOrder());
            Double currentRevenue = revenueByMonth.getOrDefault(month, 0.0);
            revenueByMonth.put(month, currentRevenue + order.getTotalPrice());
        }

        // Tạo danh sách labels và values cho chart
        List<String> labels = allMonths.stream()
                .map(YearMonth::toString)
                .collect(Collectors.toList());

        List<Double> values = allMonths.stream()
                .map(revenueByMonth::get)
                .collect(Collectors.toList());

        Map<String, Object> chartData = new HashMap<>();
        chartData.put("labels", labels);
        chartData.put("values", values);

        return chartData;
    }

}
