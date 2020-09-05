package de.hska.iwi.vislab.api_role.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
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

public class ConsumeCoreRole {
    
    // private String urlCoreProduct = "http://localhost:8084/role";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreRole.class);
    RestTemplate restTemplate = new RestTemplate();

    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        //BaseOAuth2ProtectedResourceDetails details = new BaseOAuth2ProtectedResourceDetails();
        //ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

        details.setClientId("coreRoleId");
        details.setClientSecret("coreRoleSecret");
        details.setAccessTokenUri("http://localhost:8300/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Core_Role");

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
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            OAuth2RestTemplate restTemplate3 = foo();
            return restTemplate3.getForObject(urlBuilder.getBaseUrl(), Role[].class);
            //return restTemplate.getForObject(urlBuilder.getBaseUrl(), Role[].class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public Role getRole(String input) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            OAuth2RestTemplate restTemplate3 = foo();
            return restTemplate3.getForObject(urlBuilder.getInputUrl(input), Role.class);
            //return restTemplate.getForObject(urlBuilder.getInputUrl(input), Role.class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void addRole(Role role) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            HttpEntity<Role> request = new HttpEntity<>(new Role(role.getType(), role.getLevel()));
            OAuth2RestTemplate restTemplate3 = foo();
            restTemplate3.postForLocation(urlBuilder.getBaseUrl(), request);
            //restTemplate.postForLocation(urlBuilder.getBaseUrl(), request);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void updateRole(Role role) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getUrlWithId(role.getId()));
            // HttpEntity<Role> request = new HttpEntity<>(new Role(role.getId(), role.getType(), role.getLevel()));
            OAuth2RestTemplate restTemplate3 = foo();
            restTemplate3.put(urlBuilder.getUrlWithId(role.getId()), role);
            //restTemplate.put(urlBuilder.getUrlWithId(role.getId()), role);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteRole(int id){
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("delete URL to core_role:" + urlBuilder.getUrlWithId(id), id);
            OAuth2RestTemplate restTemplate3 = foo();
            restTemplate3.delete(urlBuilder.getUrlWithId(id), id);
            //restTemplate.delete(urlBuilder.getUrlWithId(id), id);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteAllRoles(){
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            OAuth2RestTemplate restTemplate3 = foo();
            restTemplate3.delete(urlBuilder.getBaseUrl());
            //restTemplate.delete(urlBuilder.getBaseUrl());
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}