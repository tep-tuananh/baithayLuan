package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "categoryName",unique = true,nullable = false,columnDefinition = "nvarchar(50)")
    private String categoryName;
    @Column(columnDefinition = "boolean  default true")
    private Boolean status;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
