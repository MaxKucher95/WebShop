package de.hska.iwi.vislab.comp_user_role.ConsumingREST;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class UrlBuilder {

    private String baseUrl_core_role;
    private String baseUrl_core_user;

    String getBaseUrl_core_user(){
        return baseUrl_core_user;
    }
    String getBaseUrl_core_role(){
        return baseUrl_core_role;
    }

    String getUserUrl(){return baseUrl_core_user+"/user";};
    String getRoleUrl(){return baseUrl_core_role + "/role";}
    String getRoleUrl_admin(){return baseUrl_core_role + "/role/admin";}
    String getRoleUrl_user(){return baseUrl_core_role + "/role/user";}

    public UrlBuilder(){
        LoadBalancerClient loadBalancer = BeanUtil.getBean(LoadBalancerClient.class);
        ServiceInstance si_core_user = loadBalancer.choose("core_user");
        ServiceInstance si_core_role = loadBalancer.choose("core_role");
        try{
          this.baseUrl_core_user =  si_core_user.getUri().toString();
          this.baseUrl_core_role = si_core_role.getUri().toString();
        }catch(NullPointerException np_ex){
          this.baseUrl_core_user = "http://coreuser:8101";
          this.baseUrl_core_role = "http://corerole:8100";
        }
    }


}