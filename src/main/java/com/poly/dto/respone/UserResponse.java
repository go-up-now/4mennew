package com.poly.dto.respone;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String username;
//    String password;
    String email;
    String phone;
    String address;
    String fullname;
    Boolean gender;
    LocalDate birthday;
    String img;
    String state;
    LocalDateTime createdAt;
    Set<RoleResponse> roles;
}
