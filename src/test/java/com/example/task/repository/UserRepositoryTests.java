package com.example.task.repository;

import com.example.task.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkIfUserRepositorySavesANewUserToTheDataBase(){
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();

        User savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void checkIfUserRepositoryFindsAnExistingUserByEmail(){
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();

        User savedUser = userRepository.save(user);
        boolean userEmail = userRepository.findFirstByEmail(savedUser.getEmail()).isPresent();
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(userEmail).isTrue();
    }

    @Test
    public void checkIfUserRepositoryFindsAUserByTheirEmailAndPassword(){
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();

        User savedUser = userRepository.save(user);
     Optional<User> userEmailAndPassword =   userRepository
             .findUserByEmailAndPassword(savedUser.getEmail(),savedUser.getPassword());
     Assertions.assertThat(savedUser).isNotNull();
     Assertions.assertThat(userEmailAndPassword).isPresent();
    }
}
