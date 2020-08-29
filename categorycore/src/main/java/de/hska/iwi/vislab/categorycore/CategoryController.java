package de.hska.iwi.vislab.categorycore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableCircuitBreaker
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public Category[] getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @PostMapping(path = "/category", consumes = "application/json")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping(path = "/category/{id}", consumes = "application/json")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }

    @DeleteMapping("/category")
    public void deleteCategory() {
        categoryService.deleteAllCategories();
    }
}