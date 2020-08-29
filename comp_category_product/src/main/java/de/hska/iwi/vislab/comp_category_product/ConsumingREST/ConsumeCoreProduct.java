package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ConsumeCoreProduct {

    // private String urlCoreProduct = "http://localhost:8080/product";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreProduct.class);
    RestTemplate restTemplate = new RestTemplate();

    public Product[] getProducts() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getProductUrl());
            Product[] products = restTemplate.getForObject(urlBuilder.getProductUrl(), Product[].class);
            return products;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void addProduct(String name, Double price, Integer categoryId, String details) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getProductUrl());
            Product product = new Product(name, price, categoryId, details);
            restTemplate.postForLocation(urlBuilder.getProductUrl(), product);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteProduct(int id) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getProductDeleteByIdUrl(id));
            restTemplate.delete(urlBuilder.getProductDeleteByIdUrl(id));
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}