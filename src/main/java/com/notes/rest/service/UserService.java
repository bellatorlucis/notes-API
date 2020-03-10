package com.notes.rest.service;

import com.notes.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public Optional<User> findUserById(int id);
}
