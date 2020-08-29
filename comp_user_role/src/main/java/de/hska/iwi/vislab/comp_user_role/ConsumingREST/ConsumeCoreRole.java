package de.hska.iwi.vislab.comp_user_role.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ConsumeCoreRole {
    

    //private String urlCoreProduct = "http://localhost:8084/role";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreRole.class);
    RestTemplate restTemplate = new RestTemplate();

    public int getRoleIdForUser() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("getRoleUrl_user():" + urlBuilder.getRoleUrl_user());
            Role role = restTemplate.getForObject(urlBuilder.getRoleUrl_user(), Role.class);
            log.info("roleId:" + role.getId());
            return role.getId();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }


}