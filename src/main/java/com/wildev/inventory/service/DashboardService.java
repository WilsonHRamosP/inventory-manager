package com.wildev.inventory.service;

import com.wildev.inventory.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface DashboardService {

    long getTotalProducts();

    long getTotalCategories();

    long getTotalSales();

    BigDecimal getTotalRevenue();

    List<Product> getLowStockProducts();
}