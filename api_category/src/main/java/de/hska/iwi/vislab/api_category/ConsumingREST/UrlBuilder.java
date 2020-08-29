package de.hska.iwi.vislab.api_category.ConsumingREST;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class UrlBuilder {

    private String baseUrl_core_category;
    private String baseUrl_comp_product_category;

    String getBaseUrl_core_category(){
        return baseUrl_core_category;
    }
    String getBaseUrl_comp_product_category(){
        return baseUrl_comp_product_category;
    }

    String getCategoryUrl(){return getBaseUrl_core_category() + "/category";}
    String getCategoryUrl_withId(int id){return getCategoryUrl() + "/"+id;}

    String getCompositeServiceUrl(){return getBaseUrl_comp_product_category() + "/comp_category_product/category";}
    String getCompositeServiceUrl_withId(int id){return getCompositeServiceUrl() + "/"+id;}

    public UrlBuilder(){
        LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
        ServiceInstance si_core_category = loadBalancer.choose("core_category");
        ServiceInstance si_comp_product_category = loadBalancer.choose("comp_category_product");
        try{
            this.baseUrl_core_category =  si_core_category.getUri().toString();
            this.baseUrl_comp_product_category = si_comp_product_category.getUri().toString();
        }catch(NullPointerException NP_ex){
            this.baseUrl_core_category = "http://localhost:8200";// Schreibweise siehe Docker-Compose
            this.baseUrl_comp_product_category = "http://localhost:8202";// Schreibweise siehe Docker-Compose
        }
    }
}