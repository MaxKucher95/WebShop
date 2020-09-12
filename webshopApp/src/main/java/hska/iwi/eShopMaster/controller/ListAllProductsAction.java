package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.*;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final Logger log = LoggerFactory.getLogger(ListAllProductsAction.class);
	private static final long serialVersionUID = -94109228677381902L;
	
	User user;
	private List<Product> products_old;
	private List<Category> categories;
	private List<Product_Frontend> products = new ArrayList();
	
	public String execute() throws Exception{
		String result = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("webshop_user");
		
		if(user != null){
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			this.products_old = productManager.getProducts();
			result = "success";
		}

		CategoryManager categoryManager = new CategoryManagerImpl();
		categories = categoryManager.getCategories();

		log.info("Anzahl Categories: "+categories.size());
		log.info("Modify List for Frontend User");
		for(Product p : products_old){
			log.info("Product Name"+p.getName());
			for(Category c : categories) {
				if(c.getId() == p.getCategoryId()) {
					log.info("Product Name"+p.getName() + " Category Name "+c.getName());
					Product_Frontend pf = new Product_Frontend();
					pf.setName(p.getName());
					pf.setPrice(p.getPrice());
					pf.setDetails(p.getDetails());
					pf.setCategory(c.getName());
					products.add(pf);
				}
			}
		}
		
		return result;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Product> getProducts_old() {
		return products_old;
	}

	public void setProducts_old(List<Product> products) {
		this.products_old = products;
	}

	public List<Product_Frontend> getProducts() {
		return products;
	}

	public void setProducts(List<Product_Frontend> productsF) {
		this.products = products;
	}

}
