package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
