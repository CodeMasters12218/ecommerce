package com.example.product.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a product available in the catalog.")
public class Product {
    @Schema(description = "Unique identifier of the product", example = "300")
    private Long id;

    @Schema(description = "Name of the product", example = "Gaming Keyboard")
    private String name;

    @Schema(description = "Price of the product", example = "79.99")
    private Double price;

    public Product() {}

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

