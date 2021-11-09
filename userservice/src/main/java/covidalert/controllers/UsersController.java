package covidalert.controllers;


import covidalert.config.KeyCloakConfig;
import covidalert.models.User;
import covidalert.repositories.UserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    Keycloak keycloak = KeyCloakConfig.getInstance();
    UsersResource userResource = keycloak.realm("microservices-realm").users();

    @GetMapping
    public List<UserRepresentation> list() {
        return userResource.list();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public UserRepresentation get(@PathVariable String username) {
        return userResource.search(username).get(0);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRepresentation create(@RequestBody final User user) {
        // Create user representation
        UserRepresentation createdUser = new UserRepresentation();
        createdUser.setUsername(user.getUsername());
        createdUser.setEmail(user.getEmail());
        createdUser.setFirstName(user.getFirst_name());
        createdUser.setLastName(user.getLast_name());
        createdUser.setEnabled(true);

        // Save user and fetch id
        Response response = userResource.create(createdUser);
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(user.getPassword());

        // Save password credentials
        userResource.get(userId).resetPassword(passwordCred);

        return createdUser;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s'il faut supprimer aussi
        // les enregistrements enfants
        userRepository.deleteById(id);
    }
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody @Valid User user) {
        User existingUser = userRepository.getById(id);
        // Le dernier argument permet d'empêcher l'alteration de l'id du user dans la base de donnée
        BeanUtils.copyProperties(user, existingUser, "user_id");
        return userRepository.saveAndFlush(existingUser);
    }



}
