package com.example.product.domain.model;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Event representation for product-related actions.")
public class ProductEvent implements Serializable {
    @Schema(description = "Unique identifier of the product", example = "300")
    private Long id;

    @Schema(description = "Name of the product", example = "Gaming Keyboard")
    private String name;

    public ProductEvent() {}
    public ProductEvent(Long id, String name) {
        this.id = id;
        this.name = name;
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

    
}

