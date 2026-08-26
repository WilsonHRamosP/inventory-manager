package com.wildev.inventory.repository;

import com.wildev.inventory.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT COALESCE(SUM(s.total), 0) FROM Sale s")
    BigDecimal sumTotal();
}
