package com.example.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.product.infrastructure.repository.JpaProductRepository;
import com.example.product.infrastructure.repository.ProductEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private JpaProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        ProductEntity product = new ProductEntity();
        product.setName("Laptop");
        product.setPrice(999.99);

        ProductEntity saved = productRepository.save(product);

        assertThat(saved.getId()).isNotNull();

        ProductEntity found = productRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Laptop");
        assertThat(found.getPrice()).isEqualTo(999.99);
    }
}
