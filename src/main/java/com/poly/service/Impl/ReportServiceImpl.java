package com.poly.service.Impl;

import com.poly.report.ReportRevenueByCategory;
import com.poly.report.ReportRevenueByDay;
import com.poly.report.ReportRevenueByUser;
import com.poly.repository.OrderDetailRepository;
import com.poly.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<ReportRevenueByCategory> getReportRevenueByCategory() {
        return orderDetailRepository.getRevenueByCategories();
    }

    @Override
    public List<ReportRevenueByUser> getReportRevenueByUser() {
        return orderDetailRepository.getRevenueByUser();
    }

    @Override
    public List<ReportRevenueByDay> getReportRevenueByDay() {
        return orderDetailRepository.getRevenueByDay();
    }
}
