package com.wildev.inventory.service.impl;

import com.wildev.inventory.entity.Product;
import com.wildev.inventory.repository.CategoryRepository;
import com.wildev.inventory.repository.ProductRepository;
import com.wildev.inventory.repository.SaleRepository;
import com.wildev.inventory.service.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SaleRepository saleRepository;

    public DashboardServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            SaleRepository saleRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public long getTotalProducts() {
        return productRepository.count();
    }

    @Override
    public long getTotalCategories() {
        return categoryRepository.count();
    }

    @Override
    public long getTotalSales() {
        return saleRepository.count();
    }

    @Override
    public BigDecimal getTotalRevenue() {
        return saleRepository.sumTotal();
    }

    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findByStockLessThanEqual(5);
    }
}