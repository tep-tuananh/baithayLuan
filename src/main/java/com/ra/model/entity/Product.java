package com.ra.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName",columnDefinition = "nvarchar(50)",nullable = false,unique = true)
    private String productName;
    @Column(name = "price")
    private Double price;
    @Column(name = "image",columnDefinition = "nvarchar(100)",nullable = false)
    private String image;
    @Column(name = "productStatus", columnDefinition = "boolean  default true")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
}
