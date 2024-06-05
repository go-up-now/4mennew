package com.poly.api;

import com.poly.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/report")
public class ReportControllerApi {
    @Autowired
    ReportService reportService;

    @GetMapping("/revenue/category")
    public ResponseEntity<?> getRevenueReportByCategory(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get revenue report by category success");
            result.put("data", reportService.getReportRevenueByCategory());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/users")
    public ResponseEntity<?> getRevenueReportByUser(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get revenue report by user success");
            result.put("data", reportService.getReportRevenueByUser());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/revenue/days")
    public ResponseEntity<?> getRevenueReportByDay(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("success", true);
            result.put("messages", "get revenue report by day success");
            result.put("data", reportService.getReportRevenueByDay());
        }
        catch (Exception e) {
            result.put("success", false);
            result.put("messages", e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}
