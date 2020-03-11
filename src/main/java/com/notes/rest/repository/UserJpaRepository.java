package com.notes.rest.repository;

import com.notes.rest.model.Note;
import com.notes.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

    @Query("FROM User user JOIN FETCH user.notes n WHERE user.userId=:id")
    Optional<User> findAllNotesWithUserId(@Param("id") Integer id);
}
