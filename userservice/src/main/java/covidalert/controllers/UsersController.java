package covidalert.controllers;


import covidalert.config.KeyCloakConfig;
import covidalert.models.User;
import covidalert.repositories.UserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    Keycloak keycloak = KeyCloakConfig.getInstance();
    RealmResource userResource = keycloak.realm("microservices-realm");

    @GetMapping
    public List<UserRepresentation> list() {
        return userResource.users().list();
    }

//    @GetMapping
//    @RequestMapping("{id}")
//    public User get(@PathVariable Long id) {
//
//    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return  userRepository.saveAndFlush(user);
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
