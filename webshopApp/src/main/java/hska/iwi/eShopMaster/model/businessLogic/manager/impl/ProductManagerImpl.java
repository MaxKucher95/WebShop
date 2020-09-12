package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiCategory;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.ConsumeApiProduct;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManagerImpl implements ProductManager {

	private static final Logger log = LoggerFactory.getLogger(ProductManagerImpl.class);
	
	ConsumeApiProduct apiProduct = new ConsumeApiProduct();

	public List<Product> getProducts() {
		log.info("getProducts called");
		Product[] products = apiProduct.getProducts();
		List<Product> list = new ArrayList<Product>(products.length);
		for (Product product : products) {
			list.add(product);
		}
		return list;
	}
	
	public List<Product> getProductsForSearchValues(String searchDescription,
			String searchMinPrice, String searchMaxPrice) {
		log.info("getProductsForSearchValues called - Params:" + searchDescription + " " + searchMinPrice+" "+searchMaxPrice);
		Product[] products = apiProduct.findProduct(Optional.of(searchDescription), Optional.of(searchMinPrice.toString()), Optional.of(searchMaxPrice.toString()));
		List<Product> list = new ArrayList(products.length);
		for (Product product : products) {
			list.add(product);
		}
		return list;
	}

	public Product getProductById(int id) {
		log.info("getProductById called - Params:" + id);
		return apiProduct.getProduct(id);
	}

	public Product getProductByName(String name) {
		log.info("getProductByName called - Params:" + name);
		Product[] products = apiProduct.findProduct(Optional.of(name), Optional.ofNullable(null), Optional.ofNullable(null));
		if (products != null && products.length >= 1) {
			return products[0];
		}
		return null;
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		log.info("addProduct called - Params:" + name + " "+price+" "+categoryId+" "+details);
		Product product = new Product(name,price,categoryId, details);
		apiProduct.addProduct(product);
		return 1; // TODO
	}
	

	public void deleteProductById(int id) {
		log.info("deleteProductById called - Params:" + id);
		apiProduct.deleteProduct(id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		log.info("deleteProductsByCategoryId called - Params:" + categoryId);
		ConsumeApiCategory apiCategory = new ConsumeApiCategory();
		apiCategory.deleteCategory(categoryId);
		return true;
	}

}
