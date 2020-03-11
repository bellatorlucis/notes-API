package com.notes.rest.service;

import com.notes.rest.exception.UserAlreadyExistsException;
import com.notes.rest.model.User;
import com.notes.rest.repository.UserJpaRepository;
import com.notes.rest.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserJpaRepository userJpaRepository;

    public User saveUser(User user){
        if(userJpaRepository.findUserByUsername(user.getUsername()) != null)
            throw new UserAlreadyExistsException(user.getUsername());

        return userJpaRepository.saveAndFlush(user);
    }

    public List<User> getAllUsers(){
       return userJpaRepository.findAll();
    }

    public Optional<User> findUserById(int id){
        return userJpaRepository.findById(id);
    }

    public Optional<User> findUserBydAndAllNotes(int id){
        return userJpaRepository.findAllNotesWithUserId(id);
    }

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
}
