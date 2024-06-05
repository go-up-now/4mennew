package com.poly.report;

import com.poly.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportRevenueByCategory implements Serializable {
    Category category;
    Long revenue;
    Long quantity;
}
