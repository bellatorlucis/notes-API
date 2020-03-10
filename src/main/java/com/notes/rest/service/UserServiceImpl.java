package com.notes.rest.service;

import com.notes.rest.exception.UserAlreadyExistsException;
import com.notes.rest.exception.UserNotFoundException;
import com.notes.rest.model.User;
import com.notes.rest.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static ofNullable

import java.util.Optional;

@Service
public class UserService {
    private UserJpaRepository userJpaRepository;

    public User saveUser(User user){
        ofNullable
        if(userJpaRepository.findUserByUsername(user.getUsername()) != null)
            throw new UserAlreadyExistsException(user.getUsername());

        return userJpaRepository.save(user);
    }

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
}
