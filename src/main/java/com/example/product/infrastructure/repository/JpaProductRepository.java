package com.example.product.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByPriceLessThan(Double price);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM ProductEntity p WHERE p.price BETWEEN :min AND :max")
    List<ProductEntity> buscarPorRangoPrecio(@Param("min") Double min, @Param("max") Double max);
}
