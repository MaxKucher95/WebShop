package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "{ id=" + id + ", name=" + name + " }";
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}