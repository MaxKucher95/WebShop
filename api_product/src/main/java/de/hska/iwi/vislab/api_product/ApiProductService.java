package de.hska.iwi.vislab.api_product;

import java.util.Optional;

import de.hska.iwi.vislab.api_product.ConsumingREST.UrlBuilder;
import org.springframework.stereotype.Service;

import de.hska.iwi.vislab.api_product.ConsumingREST.ConsumeCompCategoryProduct;
import de.hska.iwi.vislab.api_product.ConsumingREST.ConsumeCoreProduct;
import de.hska.iwi.vislab.api_product.ConsumingREST.Product;
import java.io.IOException;
import org.springframework.web.client.RestClientException;

/**
 * The implementation of the service.
 */
@Service
public class ApiProductService {

    ConsumeCompCategoryProduct compProductCategory = new ConsumeCompCategoryProduct();

    public void addProduct(Product payload) {
        compProductCategory.addProduct(payload);
    }

    ConsumeCoreProduct coreProduct = new ConsumeCoreProduct();

    public Product[] getProducts() {
        return coreProduct.getProducts();
    }

    public void deleteProduct(int id) {
        coreProduct.deleteProduct(id);
    }

    public void updateProduct(int id, String name, double price, int categoryId, String details) {
        Product product = coreProduct.getProduct(id);
        product.setCategoryId(categoryId);
        product.setDetails(details);
        product.setName(name);
        product.setPrice(price);
        coreProduct.updateProduct(product);
    }

    public void deleteAllProducts() {
        coreProduct.deleteAllProducts();
    }

    public Product getProduct(int id) {
        return coreProduct.getProduct(id);
    }

    public Product[] findProduct(Optional<String> searchValue, Optional<String> priceMinValue,
            Optional<String> priceMaxValue) {
        return coreProduct.findProduct(searchValue, priceMinValue, priceMaxValue);
    }

}