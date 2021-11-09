/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.api.service;

import com.usa.api.model.Category;
import com.usa.api.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);
        } else {
            Optional<Category> categoryAux = categoryRepository.getCategory(category.getId());
            if (categoryAux.isEmpty()) {
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }

    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> categoryAux = categoryRepository.getCategory(category.getId());
            if (!categoryAux.isEmpty()) {
                if (category.getName() != null) {
                    categoryAux.get().setName(category.getName());
                }
                if (category.getDescription() != null) {
                    categoryAux.get().setDescription(category.getDescription());
                }
                return categoryRepository.save(categoryAux.get());
            }
        }
        return category;
    }

    public boolean delete(int id) {
        Optional<Category> categoryAux = categoryRepository.getCategory(id);
        if (!categoryAux.isEmpty()) {
            categoryRepository.delete(categoryAux.get());
            return true;
        }
        return false;
    }
}
