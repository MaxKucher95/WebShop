package de.hska.iwi.vislab.productcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@EnableCircuitBreaker
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public Product[] getAllProduct() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = { "/product/find" }, method = RequestMethod.GET)
    public Product[] getProducts(@RequestParam(value = "searchValue", required = false) Optional<String> searchValue,
            @RequestParam(value = "priceMinValue", required = false) Optional<String> priceMinValue,
            @RequestParam(value = "priceMaxValue", required = false) Optional<String> priceMaxValue) {
        Object[] args = new Object[3];
        if (searchValue.isPresent())
            args[0] = searchValue;
        if (priceMinValue.isPresent())
            args[1] = priceMinValue;
        if (priceMaxValue.isPresent())
            args[2] = priceMaxValue;

        return productService.getAllProducts(args);

    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping(path = "/product", consumes = "application/json")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping(path = "/product/{id}", consumes = "application/json")
    public void updateProduct(@PathVariable int id, @RequestBody(required = true) Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @DeleteMapping("/product")
    public void deleteProduct() {
        productService.deleteAllProducts();
    }
}