package com.attijariwafabank.obfactory.controller;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attijariwafabank.obfactory.configuration.KeycloakProvider;
import com.attijariwafabank.obfactory.model.dto.CreateUserRequest;
import com.attijariwafabank.obfactory.model.dto.LoginRequest;
import com.attijariwafabank.obfactory.service.KeycloakAdminClientService;

@RestController
@RequestMapping("/api/v1")
public class KeyUserController {
    @Autowired
    private KeycloakAdminClientService kcAdminClient;

    @Autowired
    private KeycloakProvider kcProvider;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(KeyUserController.class);


    // public KeyUserController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
    //     this.kcProvider = kcProvider;
    //     this.kcAdminClient = kcAdminClient;
    // }
	

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createUser(@RequestBody CreateUserRequest user) {
        Response createdResponse = kcAdminClient.createKeycloakUser(user);
        return ResponseEntity.ok(createdResponse);

    }

    @PostMapping("/users/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (Exception ex) {
            LOG.warn("invalid account. User probably hasn't verified email.", ex);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }
    
    @GetMapping(value = "/users/getLogin")
    public LoginRequest helloWordl(){
        LoginRequest rp = new LoginRequest();
        rp.setUsername("diamef");
        rp.setPassword("password");
        return rp;
    }

    @GetMapping(value = "/proteger/getLogin")
    public LoginRequest helloWordl2(){
        LoginRequest rp = new LoginRequest();
        rp.setUsername("daimef");
        rp.setPassword("password");
        return rp;
    }

}