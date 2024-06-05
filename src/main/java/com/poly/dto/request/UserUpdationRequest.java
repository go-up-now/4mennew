package com.poly.dto.request;

import com.poly.entity.Role;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdationRequest {
    String password;
    String email;
    String phone;
    String address;
    String fullname;
    Boolean gender;
    LocalDate birthday;
    String img;
    String state;
    Integer role;
    LocalDateTime updatedAt;
}
