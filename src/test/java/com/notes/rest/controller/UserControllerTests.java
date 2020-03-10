package com.notes.rest.controller;

import com.notes.rest.model.User;
import com.notes.rest.service.UserService;
import jdk.jfr.ContentType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.ResultActions;

import java.net.http.HttpHeaders;
import java.util.Optional;


@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private static String BASE_PATH = "http://localhost/";
    private static String USERS_PATH = "users";


    @Test
    public void getUserWithIdCorrectResponse() throws Exception{
        User user = new User(1, "petar");
        given(userService.findUserById(user.getUserId())).willReturn(Optional.of(user));

        mockMvc.perform(get(BASE_PATH+USERS_PATH +"/"+ user.getUserId())
                .contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$._links.self.href", is(BASE_PATH+USERS_PATH+"/"+user.getUserId())))
                .andExpect(jsonPath("$._links.all-users.href", is(BASE_PATH + USERS_PATH)));
    }

    @Test
    public void getUSerWithIdThadDoesNotExistReturnsError() throws Exception{
        User user = new User(1, "Petar");
        given(userService.findUserById(user.getUserId())).willReturn(Optional.empty());

        mockMvc.perform(get(USERS_PATH + "/" + user.getUserId())
                .contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isNotFound());
    }





}
