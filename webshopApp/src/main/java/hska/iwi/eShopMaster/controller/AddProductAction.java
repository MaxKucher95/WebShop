package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddProductAction extends ActionSupport {

	private static final long serialVersionUID = 39979991339088L;

	private String name = null;
	private String price = null;
	private int id = 0;
	private String details = null;
	private List<Category> categories;
	private Category cat = null;

	public String execute() throws Exception {
		String result = "input";
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		ActionContext.getContext().setLocale(Locale.US);

		if(user != null) {

			/*CategoryManager categoryManager = new CategoryManagerImpl();
			this.categories = categoryManager.getCategories();*/

			/*for (Category c : categories) {
				if (cat.getName() == cname) {
					this.setCategory(c);*/
			CategoryManager categoryManager = new CategoryManagerImpl();
			Category c = categoryManager.getCategory(id);

					ProductManager productManager = new ProductManagerImpl();
					int productId = productManager.addProduct(name, Double.parseDouble(price), c.getId(),
							details);

					if (productId > 0) {
						result = "success";
					}
			/*		break;
				}
			}*/
		}
		return result;
	}

	@Override
	public void validate() {
		CategoryManager categoryManager = new CategoryManagerImpl();
		this.setCategories(categoryManager.getCategories());
		// Validate name:

		if (getName() == null || getName().length() == 0) {
			addActionError(getText("error.product.name.required"));
		}

		// Validate price:

		if (String.valueOf(getPrice()).length() > 0) {
			if (!getPrice().matches("[0-9]+(.[0-9][0-9]?)?")
					|| Double.parseDouble(getPrice()) < 0.0) {
				addActionError(getText("error.product.price.regex"));
			}
		} else {
			addActionError(getText("error.product.price.required"));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return cat;
	}

	public void setCategory(Category cat) {
		this.cat = cat;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
