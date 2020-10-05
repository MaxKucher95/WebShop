package de.hska.iwi.vislab.comp_category_product.ConsumingREST;

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

public class ConsumeCoreProduct {

  public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
    // BaseOAuth2ProtectedResourceDetails details = new
    // BaseOAuth2ProtectedResourceDetails();
    // ResourceOwnerPasswordResourceDetails details = new
    // ResourceOwnerPasswordResourceDetails();
    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();

    details.setClientId("coreProductId");
    details.setClientSecret("coreProductSecret");
    details.setAccessTokenUri("http://oauthserver:8300/oauth/token");
    // details.setGrantType("client_credentials");
    List<String> scope = new ArrayList<>();
    scope.add("read");
    scope.add("write");
    details.setScope(scope);
    details.setAuthenticationScheme(AuthenticationScheme.header);
    details.setClientAuthenticationScheme(AuthenticationScheme.header);
    details.setId("1");
    details.setTokenName("Core_Product");

    // log.info("OAUTH DETAILS" + clientId + clientSecret +
    // details.getAccessTokenUri() + details.getGrantType());
    // details.set
    return details;
  }

  @Bean // has to be done at runtime because the authorization server would not be up
        // otherwise
  public OAuth2RestTemplate foo() {
    AccessTokenRequest atr = new DefaultAccessTokenRequest();
    return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
  }

  private static final Logger log = LoggerFactory.getLogger(ConsumeCoreProduct.class);
  RestTemplate restTemplate = new RestTemplate();

  public Product[] getProducts() {
    try {
      UrlBuilder urlBuilder = new UrlBuilder();
      log.info("URL:" + urlBuilder.getProductUrl());
      OAuth2RestTemplate restTemplate3 = foo();
      Product[] products = restTemplate3.getForObject(urlBuilder.getProductUrl(), Product[].class);
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
      OAuth2RestTemplate restTemplate3 = foo();
      restTemplate3.postForLocation(urlBuilder.getProductUrl(), product);
    } catch (Exception e) {
      System.out.println(e);
      throw e;
    }
  }

  public void deleteProduct(int id) {
    try {
      UrlBuilder urlBuilder = new UrlBuilder();
      log.info("URL:" + urlBuilder.getProductDeleteByIdUrl(id));
      OAuth2RestTemplate restTemplate3 = foo();
      restTemplate3.delete(urlBuilder.getProductDeleteByIdUrl(id));
    } catch (Exception e) {
      System.out.println(e);
      throw e;
    }
  }
}