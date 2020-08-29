package de.hska.iwi.vislab.api_category;

import org.springframework.stereotype.Service;

import de.hska.iwi.vislab.api_category.ConsumingREST.Category;
import de.hska.iwi.vislab.api_category.ConsumingREST.ConsumeCompCategoryProduct;
import de.hska.iwi.vislab.api_category.ConsumingREST.ConsumeCoreCategory;

/**
 * The implementation of the service.
 */
@Service
public class ApiCategoryService {

    ConsumeCompCategoryProduct compProductCategory = new ConsumeCompCategoryProduct();

    public void deleteCategory(int id) {
        compProductCategory.deleteCategory(id);
    }

    ConsumeCoreCategory coreCategory = new ConsumeCoreCategory();

    public Category getCategory(int id) {
        return coreCategory.getCategory(id);
    }

    public Category[] getCategories() {
        return coreCategory.getCategories();
    }

    public void postCategory(String name) {
        coreCategory.addCategory(new Category(-1, name));
    }

    public void updateCategory(int id, String name) {
        Category cat = new Category(id, name);
        coreCategory.updateCategory(cat);
    }
}