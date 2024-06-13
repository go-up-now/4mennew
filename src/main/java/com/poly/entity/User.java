package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fullname;

    @Column(unique = true)
    String username;
    String password;
    Boolean gender;
    String email;
    String phone;
    String address;
    LocalDate birthday;
    String img;
    String state;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToMany
    Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<Order> orders;


}
