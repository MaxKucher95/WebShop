package de.hska.iwi.vislab.comp_user_role;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import de.hska.iwi.vislab.comp_user_role.ConsumingREST.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableCircuitBreaker
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    @PostMapping(path="/comp_user_role/user", consumes = "application/json")
    public void register(@RequestBody(required = true) User request) {
        userRoleService.register(request.getFirstname(), request.getLastname(), request.getUsername(), request.getPassword());
    }

}