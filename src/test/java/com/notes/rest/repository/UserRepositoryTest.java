package com.notes.rest.repository;


import com.notes.rest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    public void save_CorrectUsername_UserRetrived(){
        User user = new User();
        user.setUsername("Milan");
        entityManager.persist(user);
        entityManager.flush();

        User foundUser = userJpaRepository.findUserByUsername(user.getUsername());

        assertThat(foundUser.getUserId()).isEqualTo(user.getUserId());
        assertThat(foundUser.getUsername()).isEqualTo(foundUser.getUsername());
    }
}
