package com.wildev.inventory.service.impl;

import com.wildev.inventory.entity.Category;
import com.wildev.inventory.repository.CategoryRepository;
import com.wildev.inventory.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category existingCategory = findById(id);

        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}