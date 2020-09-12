package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product_Frontend;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6565401833074694229L;
	
	
	private String searchDescription = null;
	private String searchMinPrice;
	private String searchMaxPrice;
	
	private Double sMinPrice = null;
	private Double sMaxPrice = null;
	
	private User user;
	private List<Product> products_old;
	private List<Product_Frontend> products = new ArrayList();
	private List<Category> categories;
	

	public String execute() throws Exception {
		System.out.println("Hello");
		
		String result = "input";
		
		// Get user:
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		ActionContext.getContext().setLocale(Locale.US);  
		
		if(user != null){
			// Search products and show results:
			ProductManager productManager = new ProductManagerImpl();
//			this.products = productManager.getProductsForSearchValues(this.searchDescription, this.searchMinPrice, this.searchMaxPrice);
			/*if (!searchMinPrice.isEmpty()){
				sMinPrice =  Double.parseDouble(this.searchMinPrice);
			}
			if (!searchMaxPrice.isEmpty()){
				sMaxPrice =  Double.parseDouble(this.searchMaxPrice);
			}*/
			this.products_old = productManager.getProductsForSearchValues(this.searchDescription, searchMinPrice, searchMaxPrice);


			CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();


			for(Product p : products_old){
				for(Category c : categories) {
					if(c.getId() == p.getCategoryId()) {
						Product_Frontend pf = new Product_Frontend();
						pf.setName(p.getName());
						pf.setPrice(p.getPrice());
						pf.setDetails(p.getDetails());
						pf.setCategory(c.getName());
						products.add(pf);
					}
				}
			}


			// Show all categories:
			/*CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();*/
			result = "success";
		}
		
		return result;
	}
			
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		public List<Product_Frontend> getProducts() {
			return products;
		}

		public void setProducts(List<Product_Frontend> products) {
			this.products = products;
		}

		public List<Product> getProducts_old() {
			return products_old;
		}

		public void setProducts_old(List<Product> product_old) {
			this.products_old = products_old;
		}
		
		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}
		
		


	public String getSearchValue() {
		return searchDescription;
	}


	public void setSearchValue(String searchValue) {
		this.searchDescription = searchValue;
	}


	public String getSearchMinPrice() {
		return searchMinPrice;
	}


	public void setSearchMinPrice(String searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}


	public String getSearchMaxPrice() {
		return searchMaxPrice;
	}


	public void setSearchMaxPrice(String searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}


//	public Double getSearchMinPrice() {
//		return searchMinPrice;
//	}
//
//
//	public void setSearchMinPrice(Double searchMinPrice) {
//		this.searchMinPrice = searchMinPrice;
//	}
//
//
//	public Double getSearchMaxPrice() {
//		return searchMaxPrice;
//	}
//
//
//	public void setSearchMaxPrice(Double searchMaxPrice) {
//		this.searchMaxPrice = searchMaxPrice;
//	}
}
