package de.hska.iwi.vislab.productcore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Double price;
    private Integer categoryId;
    private String details;

    protected Product() {
    }

    public Product(String name, Double price, Integer categoryId, String details) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.details = details;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%d, name='%s', price=%e, categoryId=%d, details='%s']", id, name, price,
                categoryId, details);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getDetails() {
        return details;
    }
}