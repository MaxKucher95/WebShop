package de.hska.iwi.vislab.comp_category_product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;
import de.hska.iwi.vislab.comp_category_product.ConsumingREST.Product;

@RestController
@EnableCircuitBreaker
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);

    /**
     * Checks if categoryId actually exists, if yes then the product is added.
     */
    @PostMapping(path = "/comp_product_category/product", consumes = "application/json")
    public void addProduct(@RequestBody(required = true) Product request) {
        productCategoryService.addProduct(request.getName(), request.getPrice(), request.getCategoryId(),
                request.getDetails());
    }

    /**
     * Delete a category and all products that were in that category.
     */
    @DeleteMapping("/comp_product_category/category/{id}")
    public void deleteCategory(@PathVariable int id) {
        productCategoryService.deleteCategory(id);
    }
}