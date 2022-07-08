package com.attijariwafabank.obfactory.service;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.account.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.attijariwafabank.obfactory.configuration.KeycloakProvider;
import com.attijariwafabank.obfactory.model.dto.CreateUserRequest;

@Service
public class KeycloakAdminClientService {
    @Value("${app.config.keycloak.realm}")
    public String realm;

    private final KeycloakProvider kcProvider;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider) {
        this.kcProvider = keycloakProvider;
    }

    public Response createKeycloakUser(CreateUserRequest user) {
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());
        UserResource userResource1 = kcProvider.getInstance().realm(realm).users().get(user.getEmail());
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getEmail());
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        // userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);
        Response response = usersResource.delete(userRepresentation.getId());
        userResource1.resetPassword(credentialRepresentation);
        return response;

    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
   
    

}