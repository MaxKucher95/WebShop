package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

	private static final Logger log = LoggerFactory.getLogger(CategoryManagerImpl.class);

	ConsumeApiCategory apiCategory = new ConsumeApiCategory();

	public List<Category> getCategories() {
		log.info("getCategories called");
		Category[] categories = apiCategory.getCategories();
		log.info("getCategories called - Result-length:" + categories.length);
		List<Category> list = new ArrayList<Category>(categories.length);
		for (Category category : categories) {
			list.add(category);
		}
		return list;
	}

	public Category getCategory(int id) {
		log.info("getCategory called - Params: "+ id);
		return apiCategory.getCategory(id);
	}

	public Category getCategoryByName(String name) {
		log.info("getCategoryByName called - Params: "+ name);
		List<Category> list = getCategories();
		for (Category cat : list) {
			if (cat.getName().equals(name)) {
				return cat;
			}
		}
		return null;
	}

	public void addCategory(String name) {
		log.info("addCategory called - Params: "+ name);
		Category category = new Category(name);
		apiCategory.addCategory(category);
	}

	public void delCategory(Category cat) {
		log.info("delCategory called - Params: "+ cat.getName());
// 		Products are also deleted because of relation in Category.java 
		apiCategory.deleteCategory(cat.getId());
	}

	public void delCategoryById(int id) {
		log.info("delCategoryById called - Params: "+ id);
		apiCategory.deleteCategory(id);
	}
}
