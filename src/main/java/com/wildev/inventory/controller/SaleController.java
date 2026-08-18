package com.wildev.inventory.controller;

import com.wildev.inventory.entity.Sale;
import com.wildev.inventory.service.ProductService;
import com.wildev.inventory.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;

    public SaleController(
            SaleService saleService,
            ProductService productService) {

        this.saleService = saleService;
        this.productService = productService;
    }

    @GetMapping
    public String listSales(Model model) {
        model.addAttribute("sales", saleService.findAll());
        return "sales/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("sale", new Sale());

        model.addAttribute(
                "products",
                productService.findAll()
                        .stream()
                        .filter(product -> product.getStock() != null
                                && product.getStock() > 0)
                        .toList()
        );

        return "sales/form";
    }

    @PostMapping("/save")
    public String saveSale(@ModelAttribute("sale") Sale sale) {

        saleService.save(sale);

        return "redirect:/sales";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Sale sale = saleService.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        model.addAttribute("sale", sale);

        model.addAttribute(
                "products",
                productService.findAll()
                        .stream()
                        .filter(product -> product.getStock() != null
                                && product.getStock() > 0)
                        .toList()
        );

        return "sales/form";
    }

    @GetMapping("/view/{id}")
    public String viewSale(
            @PathVariable Long id,
            Model model) {

        Sale sale = saleService.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada"));

        model.addAttribute("sale", sale);

        return "sales/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {

        saleService.deleteById(id);

        return "redirect:/sales";
    }
}
