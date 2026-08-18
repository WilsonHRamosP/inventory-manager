package com.wildev.inventory.service;

import com.wildev.inventory.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    Optional<Sale> findById(Long id);

    void deleteById(Long id);
}
