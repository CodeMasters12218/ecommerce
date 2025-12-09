package com.example.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.product.domain.model.Product;
import com.example.product.domain.ports.ProductServicePort;
import com.example.product.infrastructure.controller.ProductController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import java.util.Collections;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServicePort productService;

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);

        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Laptop"))
               .andExpect(jsonPath("$.price").value(1000.0));
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);

        when(productService.save(any(Product.class))).thenReturn(product);

        String jsonPayload = """
            {
              "id": 1,
              "name": "Laptop",
              "price": 1000.0
            }
            """;

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Laptop"))
               .andExpect(jsonPath("$.price").value(1000.0));
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Gaming Laptop");
        updatedProduct.setPrice(1200.0);

        when(productService.update(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        String jsonPayload = """
            {
              "id": 1,
              "name": "Gaming Laptop",
              "price": 1200.0
            }
            """;

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Gaming Laptop"))
               .andExpect(jsonPath("$.price").value(1200.0));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteById(1L);

        mockMvc.perform(delete("/api/products/1"))
               .andExpect(status().isNoContent());

        verify(productService).deleteById(1L);
    }

    @Test
    void testGetAllProducts() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);

        when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Laptop"))
               .andExpect(jsonPath("$[0].price").value(1000.0));
    }
}
