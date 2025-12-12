package com.example.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.product.infrastructure.repository.JpaProductRepository;
import com.example.product.infrastructure.repository.ProductEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryCustomTest {

    @Autowired
    private JpaProductRepository productRepository;

    @Test
    void testFindByPriceLessThan() {
        productRepository.save(new ProductEntity(null, "Mouse", 20.0));
        productRepository.save(new ProductEntity(null, "Laptop", 1000.0));

        List<ProductEntity> cheapProducts = productRepository.findByPriceLessThan(100.0);

        assertThat(cheapProducts).hasSize(1);
        assertThat(cheapProducts.get(0).getName()).isEqualTo("Mouse");
    }

    @Test
    void testBuscarPorRangoPrecio() {
        productRepository.save(new ProductEntity(null, "Tablet", 300.0));
        productRepository.save(new ProductEntity(null, "Monitor", 150.0));

        List<ProductEntity> productsInRange = productRepository.buscarPorRangoPrecio(100.0, 400.0);

        assertThat(productsInRange).hasSize(2);
    }
}

