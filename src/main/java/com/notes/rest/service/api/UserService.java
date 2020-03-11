package com.notes.rest.service.api;

import com.notes.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     User saveUser(User user);
     List<User> getAllUsers();
     Optional<User> findUserById(int id);
     Optional<User> findUserBydAndAllNotes(int id);
}
