package de.hska.iwi.vislab.comp_category_product;

import org.springframework.stereotype.Service;

import de.hska.iwi.vislab.comp_category_product.ConsumingREST.Category;
import de.hska.iwi.vislab.comp_category_product.ConsumingREST.ConsumeCoreCategory;
import de.hska.iwi.vislab.comp_category_product.ConsumingREST.ConsumeCoreProduct;
import de.hska.iwi.vislab.comp_category_product.ConsumingREST.Product;

/**
 * The implementation of the service.
 */
@Service
public class ProductCategoryService {

    public void addProduct(String name, Double price, Integer categoryId, String details) {
        ConsumeCoreCategory coreCategory = new ConsumeCoreCategory();
        Category[] categories = coreCategory.getCategories();
        loop: for (Category cat : categories) {
            if (cat.getId() == categoryId) {
                ConsumeCoreProduct coreProduct = new ConsumeCoreProduct();
                coreProduct.addProduct(name, price, categoryId, details);
                break loop;
            }
        }
    }

    public long deleteCategory(int id) {
        ConsumeCoreCategory coreCategory = new ConsumeCoreCategory();
        coreCategory.deleteCategory(id);

        ConsumeCoreProduct coreProduct = new ConsumeCoreProduct();
        Product[] products = coreProduct.getProducts();
        for (Product product : products) {
            if (product.getCategoryId() == id) {
                coreProduct.deleteProduct(id);
            }
        }
        return 0;
    }

}