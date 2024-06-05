package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int price;
    float discount;

    @Column(name = "quantity_in_stock")
    int quantityInStock;
    String description;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Galery> galeries;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderDettail> orderDettails;

//    public double getTotaPrice(){
//        return this.price - (this.price * this.discount);
//    }
}
