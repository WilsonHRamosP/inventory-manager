package com.wildev.inventory.controller;

import com.wildev.inventory.entity.Product;
import com.wildev.inventory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.wildev.inventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "products/form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "products/form";
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());

        return "products/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}