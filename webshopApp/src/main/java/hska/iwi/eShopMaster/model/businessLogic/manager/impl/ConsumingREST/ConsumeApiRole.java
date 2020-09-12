package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;


import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ConsumeApiRole {
    
    private String urlApiRole = "http://localhost:8084/role";
    
    RestTemplate restTemplate = new RestTemplate();

    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        //BaseOAuth2ProtectedResourceDetails details = new BaseOAuth2ProtectedResourceDetails();
        //ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientId("apiRoleId");
        details.setClientSecret("apiRoleSecret");
        details.setAccessTokenUri("http://localhost:8084/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Api_Role");

        //log.info("OAUTH DETAILS" + clientId + clientSecret + details.getAccessTokenUri() + details.getGrantType());
        //details.set
        return details;
    }

    @Bean // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate foo() {
        //OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oAuth2ResourceDetails());
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
        /* To validate if required configurations are in place during startup */
        //oAuth2RestTemplate.getAccessToken();
        //return oAuth2RestTemplate;
    }

    public Role[] getAllRoles() {
        OAuth2RestTemplate restTemplate3 = foo();
        return restTemplate3.getForObject(urlApiRole, Role[].class);
        //return restTemplate.getForObject(urlApiRole, Role[].class);
    }

    public Role getRole(String input) {
        OAuth2RestTemplate restTemplate3 = foo();
        return restTemplate3.getForObject(urlApiRole + "/" + input, Role.class);
        //return restTemplate.getForObject(urlApiRole + "/" + input, Role.class);
    }

    public void addRole(String typ, int level) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.postForLocation(urlApiRole, typ, level);
        //restTemplate.postForLocation(urlApiRole, typ, level);
    }

    public void updateRole(int id, String typ, int level) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.put(urlApiRole + "/" + id, typ, level);
        //restTemplate.put(urlApiRole + "/" + id, typ, level);
    }

    public void deleteRole(int id){
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.delete(urlApiRole + "/" + id);
        //restTemplate.delete(urlApiRole + "/" + id);
    }

    public void deleteAllRoles(){
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.delete(urlApiRole);
        //restTemplate.delete(urlApiRole);
    }

}