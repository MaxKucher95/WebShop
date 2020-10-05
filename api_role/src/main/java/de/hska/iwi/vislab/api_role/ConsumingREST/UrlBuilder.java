package de.hska.iwi.vislab.api_role.ConsumingREST;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


public class UrlBuilder {

    private String baseUrl;

    public UrlBuilder(){
        LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
        ServiceInstance si = loadBalancer.choose("core_role");
        try{
            this.baseUrl =  si.getUri().toString();
        }catch(NullPointerException np_ex){
            this.baseUrl = "http://corerole:8100";// Schreibweise siehe Docker-Compose
        }
    }


    String getBaseUrl(){
        return this.baseUrl + "/role";
    }

    String getSlashURL(){
        return this.baseUrl+"/";
    }

    String getUrlWithId(int id){
        return this.getBaseUrl() + "/" + id;
    }

    String getInputUrl(String input){
        return this.getBaseUrl() + "/" + input;
    }
}