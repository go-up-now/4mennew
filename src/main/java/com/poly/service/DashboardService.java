package com.poly.service;

import java.util.Map;

public interface DashboardService {
    long getTotalUsers();
    double getTotalRevenue();
    public long getTotalProducts();
    public Map<String, Object> getChartData();
}
