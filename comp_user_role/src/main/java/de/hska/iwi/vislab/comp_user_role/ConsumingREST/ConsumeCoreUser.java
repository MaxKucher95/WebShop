package de.hska.iwi.vislab.comp_user_role.ConsumingREST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


public class ConsumeCoreUser {

    //private String urlCoreUser = "http://localhost:8083/user";

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreUser.class);
    RestTemplate restTemplate = new RestTemplate();


    public void addUser(String firstname, String lastname, String username, String password, int roleId) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getUserUrl());
            HttpEntity<User> request = new HttpEntity<>(new User(firstname, lastname, username, password, roleId));
            restTemplate.postForLocation(urlBuilder.getUserUrl(), request);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public User[] getUsers() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getUserUrl());
            return restTemplate.getForObject(urlBuilder.getUserUrl(), User[].class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

}