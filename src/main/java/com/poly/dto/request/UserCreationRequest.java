package com.poly.dto.request;

import com.poly.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 6, message = "Username must be at least 6 characters")
    @Column(unique = true)
    String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
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
    LocalDateTime createdAt;
}
