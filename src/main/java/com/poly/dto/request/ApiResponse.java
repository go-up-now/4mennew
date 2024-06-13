package com.poly.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // Bỏ qua những giá trị trả về là null
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    boolean success = true;
    int code = 1000;
    String message;
    T data;
}
