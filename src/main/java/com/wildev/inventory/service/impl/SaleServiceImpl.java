package com.wildev.inventory.service.impl;

import com.wildev.inventory.entity.Product;
import com.wildev.inventory.entity.Sale;
import com.wildev.inventory.entity.SaleItem;
import com.wildev.inventory.repository.ProductRepository;
import com.wildev.inventory.repository.SaleRepository;
import com.wildev.inventory.service.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleServiceImpl(
            SaleRepository saleRepository,
            ProductRepository productRepository) {

        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Sale save(Sale sale) {

        BigDecimal total = BigDecimal.ZERO;

        Set<Long> productIds = new HashSet<>();

        // Si estamos editando, devolvemos primero
        // el stock de la venta anterior.
        if (sale.getId() != null) {

            Sale existingSale = saleRepository.findById(sale.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Venta no encontrada"));

            for (SaleItem oldItem : existingSale.getItems()) {

                Product oldProduct = oldItem.getProduct();

                oldProduct.setStock(
                        oldProduct.getStock() + oldItem.getQuantity()
                );

                productRepository.save(oldProduct);
            }
        }

        // Procesar productos de la venta
        for (SaleItem item : sale.getItems()) {

            if (item.getProduct() == null ||
                    item.getProduct().getId() == null) {

                throw new RuntimeException(
                        "Debe seleccionar un producto");
            }

            Long productId = item.getProduct().getId();

            // Evitar producto repetido
            if (!productIds.add(productId)) {

                throw new RuntimeException(
                        "El producto no puede repetirse en una venta");
            }

            Product product = productRepository.findById(productId)
                    .orElseThrow(() ->
                            new RuntimeException("Producto no encontrado"));

            Integer quantity = item.getQuantity();

            if (quantity == null || quantity <= 0) {

                throw new RuntimeException(
                        "La cantidad debe ser mayor que cero");
            }

            if (product.getStock() < quantity) {

                throw new RuntimeException(
                        "Stock insuficiente para el producto: "
                                + product.getName());
            }

            BigDecimal price = product.getPrice();

            BigDecimal subtotal = price.multiply(
                    BigDecimal.valueOf(quantity)
            );

            item.setProduct(product);
            item.setPrice(price);
            item.setSubtotal(subtotal);
            item.setSale(sale);

            total = total.add(subtotal);

            // Descontar stock
            product.setStock(
                    product.getStock() - quantity
            );

            productRepository.save(product);
        }

        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        for (SaleItem item : sale.getItems()) {

            Product product = item.getProduct();

            product.setStock(
                    product.getStock() + item.getQuantity()
            );

            productRepository.save(product);
        }

        saleRepository.delete(sale);
    }
}
