package com.poly.api;

import com.poly.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/api/")
public class DashboardControllerApi {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("totalUsers")
    public Map<String, Long> getTotalUsers() {
        return Map.of("totalUsers", dashboardService.getTotalUsers());
    }

    @GetMapping("totalProducts")
    public Map<String, Long> getTotalProducts() {
        return Map.of("totalProducts", dashboardService.getTotalProducts());
    }
    @GetMapping("totalRevenue")
    public Map<String, Double> getTotalRevenue() {
        return Map.of("totalRevenue", dashboardService.getTotalRevenue());
    }
    @GetMapping("chartData")
    public Map<String, Object> getChartData() {
        return dashboardService.getChartData();
    }
}
