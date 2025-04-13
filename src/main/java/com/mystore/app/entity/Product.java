package com.mystore.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Product {

    @Id
    private Integer id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Name must not be blank")
    private String category;

    @NotNull(message = "Cannot be null")
    @Min(value = 100, message = "Cannot be less than 100")
    @Max(value = 50000, message = "Cannot be more than 50000")
    private Double price;

    @NotNull(message = "Cannot be null")
    @Min(value = 10, message = "Cannot be less than 10")
    @Max(value = 500, message = "Cannot be more than 500")
    private Integer stockQuantity;

    public Product() {
        
    }

    public Product(Integer id, String name, String category, Double price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
