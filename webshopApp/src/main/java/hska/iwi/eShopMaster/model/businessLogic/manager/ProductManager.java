package hska.iwi.eShopMaster.model.businessLogic.manager;

import java.util.List;

import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;

public interface ProductManager {

	public List<Product> getProducts();

	public Product getProductById(int id);

	public Product getProductByName(String name);

	public int addProduct(String name, double price, int categoryId, String details);

	public List<Product> getProductsForSearchValues(String searchValue, String searchMinPrice, String searchMaxPrice);
	
	public boolean deleteProductsByCategoryId(int categoryId);
	
    public void deleteProductById(int id);
    
	
}
