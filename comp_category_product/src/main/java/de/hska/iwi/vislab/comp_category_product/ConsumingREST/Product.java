package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private int id = 999; // this value is determined by the core service
    private String name;
    private double price;
    private String details;
    private int categoryId;

    // the framework needs a default constructor for whatever reason..
    private Product() {
        name = "unknown";
        price = 0.0;
        details = "";
        categoryId = 0;
    }

    public Product(String name, double price, int categoryId, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return String.format("Product[id='%d', name='%s', price=%e, categoryId=%d, details='%s']", id, name, price, categoryId,
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
    public int getCategoryId() {
        return categoryId;
    }
}