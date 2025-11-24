package com.example.product.domain.model;

import java.io.Serializable;

public class ProductEvent implements Serializable {
    private Long id;
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

