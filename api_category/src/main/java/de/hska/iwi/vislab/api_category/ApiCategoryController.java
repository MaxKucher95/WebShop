package de.hska.iwi.vislab.api_category;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.hska.iwi.vislab.api_category.ConsumingREST.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@EnableCircuitBreaker
public class ApiCategoryController {
    private final Map<Integer, Category> categoryCache = new LinkedHashMap<Integer, Category>();
    @Autowired
    private ApiCategoryService apiCategoryService;
    private static final Logger log = LoggerFactory.getLogger(ApiCategoryController.class);

    @PostMapping(path = "/category", consumes = "application/json")
    @HystrixCommand(fallbackMethod = "addCategoryFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void addCategory(@RequestBody(required = true) Category request) {
        apiCategoryService.postCategory(request.getName());
    }

    public void addCategoryFallback(Category payload, Throwable e) {
        System.out.printf("Add Category Failed! name=%s, exception=%s%n ", payload.getName(), e);
    }

    /**
     * Delete a category and all products that were in that category.
     */
    @DeleteMapping("/category/{id}")
    @HystrixCommand(fallbackMethod = "deleteCategoryFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void deleteCategory(@PathVariable int id) {
        log.info("try to delete category...");
        apiCategoryService.deleteCategory(id);
    }

    public void deleteCategoryFallback(int id, Throwable throwable) {
        System.out.printf("Delete category failed, id=%s, exception=%s%n", id, throwable);
    }

    @GetMapping("/category")
    @HystrixCommand(fallbackMethod = "getCategoriesCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public Category[] getCategories() {
        Category[] cats = apiCategoryService.getCategories();
        for(Category cat : cats) {
            categoryCache.putIfAbsent(cat.getId(), cat);
        }
        return cats;
    }

    public Category[] getCategoriesCache() {
        Collection<Category> list = categoryCache.values();
        Category[] cats = new Category[categoryCache.values().size()];
        cats = list.toArray(cats);
        return cats;
    }

    @GetMapping("/category/{id}")
    @HystrixCommand(fallbackMethod = "getCategoryCache", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public Category getCategory(@PathVariable int id) {
        return apiCategoryService.getCategory(id);
    }

    public Category getCategoryCache(int id) {
        return categoryCache.getOrDefault(id, new Category());
    }

    @PutMapping(path = "/category/{id}", consumes = "application/json")
    @HystrixCommand(fallbackMethod = "updateCategoryFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public void updateCategory(@PathVariable int id, @RequestBody(required = true) Category request) {
        apiCategoryService.updateCategory(request.getId(), request.getName());
    }

    public void updateCategoryFallback(int id, Category payload, Throwable e) {
        System.out.printf("Update category Failed! id=%s, exception=%s%n ", id, e);
    }
}