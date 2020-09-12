package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666796923937616729L;

	private int id;
	private String name;

	public String execute() throws Exception {
		
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		
		ProductManager pm = new ProductManagerImpl();
		//pm.deleteProductById(id);
		pm.deleteProductById(pm.getProductByName(name).getId());

		/*if(user != null && (user.getRole().getTyp().equals("admin"))) {

			new ProductDAO().deleteById(id);
			{
				res = "success";
			}
		}*/
		
		return res;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
