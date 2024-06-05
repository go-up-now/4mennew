package com.poly.service;

import com.poly.report.ReportRevenueByCategory;
import com.poly.report.ReportRevenueByDay;
import com.poly.report.ReportRevenueByUser;

import java.util.List;

public interface ReportService {
    List<ReportRevenueByCategory> getReportRevenueByCategory();
    List<ReportRevenueByUser> getReportRevenueByUser();
    List<ReportRevenueByDay> getReportRevenueByDay();
}
