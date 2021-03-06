package com.notes.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.notes.rest.exception.UserNotFoundException;
import com.notes.rest.model.User;
import com.notes.rest.service.UserService;
import com.notes.rest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController()
public class UserController {
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user){
       try{
             User savedUser = userService.saveUser(user);
             EntityModel<User> userResource = new EntityModel<>(savedUser,
                                                            linkTo(methodOn(UserController.class).findUserWithId(user.getUserId())).withSelfRel());
             return ResponseEntity
                        .created(new URI(userResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                        .body(userResource);
       }catch (URISyntaxException e){
           return ResponseEntity.badRequest().body("Unable to create " + user);
       }
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<User>>> findAllUsers(){
        List<EntityModel<User>> users = userService.getAllUsers()
                                                    .stream()
                                                    .map( user -> new EntityModel<>(
                                                            user,
                                                            linkTo(methodOn(UserController.class).findUserWithId(user.getUserId())).withSelfRel(),
                                                            linkTo(methodOn(UserController.class).findAllUsers()).withRel("all-users")
                                                            ))
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(
                new CollectionModel<>(users,
                        linkTo(methodOn(UserController.class).findAllUsers()).withSelfRel()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> findUserWithId( @PathVariable int id){
        return      userService.findUserById(id)
                                .map(user -> new EntityModel<>(
                                        user,
                                        linkTo(methodOn(UserController.class).findUserWithId(user.getUserId())).withSelfRel(),
                                        linkTo(methodOn(UserController.class).findAllUsers()).withRel("all-users")))
                                .map(ResponseEntity::ok)
                                .orElseThrow(() -> new UserNotFoundException("No user with id " + id));

    }

    @Autowired
    public void setUserServiceImpl(UserService userService) {
        this.userService = userService;
    }
}
