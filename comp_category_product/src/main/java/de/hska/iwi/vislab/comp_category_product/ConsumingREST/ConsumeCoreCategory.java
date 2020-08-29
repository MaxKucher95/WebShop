package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


public class ConsumeCoreCategory {
    
    //private String urlCoreCategory = "http://localhost:8082/category";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreCategory.class);
    RestTemplate restTemplate = new RestTemplate();

    public Category[] getCategories() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getCategoryUrl());
            Category [] categories = restTemplate.getForObject(urlBuilder.getCategoryUrl(), Category[].class);
            return categories;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteCategory(int id) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getCategoryDeleteByIdUrl(id));
            restTemplate.delete(urlBuilder.getCategoryDeleteByIdUrl(id));
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}