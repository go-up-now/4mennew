package com.poly.report;

import com.poly.entity.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportProductByQuantity {
    Category category;
    Long totalPrice;
    Long quantity;
}
