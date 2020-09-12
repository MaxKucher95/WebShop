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

public class ConsumeApiUser {

    private String urlApiUser = "http://localhost:8084/user";
    
    RestTemplate restTemplate = new RestTemplate();

    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        //BaseOAuth2ProtectedResourceDetails details = new BaseOAuth2ProtectedResourceDetails();
        //ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientId("apiUserId");
        details.setClientSecret("apiUserSecret");
        details.setAccessTokenUri("http://localhost:8084/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Api_User");

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

    public User[] getAllUsers() {
        OAuth2RestTemplate restTemplate3 = foo();
        return restTemplate3.getForObject(urlApiUser, User[].class);
        //return restTemplate.getForObject(urlApiUser, User[].class);
    }

    public User getUser(String input) {
        OAuth2RestTemplate restTemplate3 = foo();
        return restTemplate3.getForObject(urlApiUser + "/" + input, User.class);
        //return restTemplate.getForObject(urlApiUser + "/" + input, User.class);
    }

    public void addUser(User user) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.postForLocation(urlApiUser, user);
        //restTemplate.postForLocation(urlApiUser, user);
    }

    public void addUser(String firstname, String lastname, String username, String password) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.postForLocation(urlApiUser, firstname, lastname, username, password);
        //restTemplate.postForLocation(urlApiUser, firstname, lastname, username, password);
    }

    public void updateUser(int id, String firstname, String lastname, String username, String password) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.put(urlApiUser + "/" + id, firstname, lastname, username, password);
        //restTemplate.put(urlApiUser + "/" + id, firstname, lastname, username, password);
    }

    public void deleteAllUsers() {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.delete(urlApiUser);
        //restTemplate.delete(urlApiUser);
    }

    public void deleteUser(int id) {
        OAuth2RestTemplate restTemplate3 = foo();
        restTemplate3.delete(urlApiUser + "/" + id);
        //restTemplate.delete(urlApiUser + "/" + id);
    }



}