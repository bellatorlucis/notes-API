package com.notes.rest.repository;

import com.notes.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
}
