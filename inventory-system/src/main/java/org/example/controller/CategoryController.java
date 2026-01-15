package org.example.controller;

import java.util.List;

import org.example.model.Category;
import org.example.repository.CategoryRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {

    private final CategoryRepository categoryRepo;

    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @GetMapping
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }


    @PostMapping
    public Category add(@RequestBody Category category) {
        return categoryRepo.save(category);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryRepo.deleteById(id);
    }


    @GetMapping("/count")
    public long count() {
        return categoryRepo.count();
    }
}