package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Oder")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fullname;
    String email;
    String phone;

    @Column(name = "address_delivery")
    String addressDelivery;

    @Column(name = "created_at")
    Date createdAt;
    int num;

    @Column(name = "total_money")
    int totalMoney;

    @Column(name = "state_oder")
    String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDettail> orderDettails;
}
