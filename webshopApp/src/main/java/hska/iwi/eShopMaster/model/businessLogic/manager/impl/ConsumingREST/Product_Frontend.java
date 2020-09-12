package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_Frontend {

    private int id = 999; // this value is determined by the core service
    private String name;
    private double price;
    private String details;
    private String category;

    // the framework needs a default constructor for whatever reason..
    public Product_Frontend() {
        name = "unknown";
        price = 0.0;
        details = "";
        category = "unknown";
    }

    public Product_Frontend(String name, double price, String category, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Product[name='%s', price=%e, categoryId=%d, details='%s']", name, price, category,
        details);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }
    /**
     * @return the categoryIdFromProduct
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

}