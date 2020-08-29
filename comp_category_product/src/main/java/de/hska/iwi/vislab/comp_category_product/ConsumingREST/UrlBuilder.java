package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


public class UrlBuilder {

    private String baseUrl_core_product;
    private String baseUrl_core_category;

    String getBaseUrl_core_product(){
        return baseUrl_core_product;
    }
    String getBaseUrl_core_category(){
        return baseUrl_core_category;
    }

    String getProductUrl(){return baseUrl_core_product+"/product";};
    String getCategoryUrl(){return baseUrl_core_category + "/category";}

    String getCategoryDeleteByIdUrl(int id){return getCategoryUrl() + "/"+id;}
    String getProductDeleteByIdUrl(int id){return getProductUrl() + "/"+id;}

    public UrlBuilder(){
        LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
        ServiceInstance si_core_product = loadBalancer.choose("core_product");
        ServiceInstance si_core_category = loadBalancer.choose("core_category");
        try{
          this.baseUrl_core_product =  si_core_product.getUri().toString();
          this.baseUrl_core_category = si_core_category.getUri().toString();
        }catch(NullPointerException np_ex){
          this.baseUrl_core_product = "http://core_product:8201";
          this.baseUrl_core_category = "http://core_category:8200";
      }    
    }
}