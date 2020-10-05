package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class ConsumeApiCategory {

    private static final Logger log = LoggerFactory.getLogger(ConsumeApiCategory.class);
    private String urlApiCategory = "http://zuul:8084/category";
    
    RestTemplate restTemplate = new RestTemplate();

    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        //BaseOAuth2ProtectedResourceDetails details = new BaseOAuth2ProtectedResourceDetails();
        //ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientId("apiCategoryId");
        details.setClientSecret("apiCategorySecret");
        details.setAccessTokenUri("http://zuul:8084/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Api_Category");

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


    public Category[] getCategories() {
        log.info("getCategories - ConsumeApiCategory - called");

        OAuth2RestTemplate restTemplate3 = foo();
        Category [] categories = restTemplate3.getForObject(urlApiCategory, Category[].class);
        //Category [] categories = restTemplate.getForObject(urlApiCategory, Category[].class);
        log.info("getCategories - ConsumeApiCategory - RESULT length: "+categories.length);
        return categories;
    }

    public Category getCategory(int id) {
        OAuth2RestTemplate restTemplate3 = foo();
        Category category = restTemplate3.getForObject(urlApiCategory + "/" + id, Category.class);
        //Category category = restTemplate.getForObject(urlApiCategory + "/" + id, Category.class);
        return category;
    }

    public void addCategory(Category category) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.postForLocation(urlApiCategory, category);
        //restTemplate.postForLocation(urlApiCategory, category);
    }

    public void updateCategory(Category category) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.put(urlApiCategory, category);
        //restTemplate.put(urlApiCategory, category);
    }

    public void deleteCategory(int id) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.delete(urlApiCategory + "/" + id);
        //restTemplate.delete(urlApiCategory + "/" + id);
    }

}