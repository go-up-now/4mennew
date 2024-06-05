package com.poly.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {
    String name;
    int price;
    float discount = 0;
    int quantityInStock;
    String description;
    LocalDateTime createdAt;
    int category;
    int brand;
}
