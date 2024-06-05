package com.poly.report;

import com.poly.entity.Category;
import com.poly.entity.User;
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
public class ReportRevenueByUser implements Serializable {
    User user;
    Long revenue;
    Long quantity;
}
