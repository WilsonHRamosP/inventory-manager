package com.wildev.inventory.repository;

import com.wildev.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStockLessThanEqual(Integer stock);
}