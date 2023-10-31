package com.example.task.service;

import com.example.task.entity.User;
import com.example.task.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;



    @Test
    public void checkIfUserServiceRegistersANewUserToTheDatabase(){
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User savedUser = userService.registerUser(user.getFullName(),user.getEmail(),user.getPassword());

        Assertions.assertThat(savedUser).isNotNull();

    }

    @Test
    public void checkIfUserServiceAuthenticatesAUserInOrderToAllowLogin(){
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();
        when(userRepository.findUserByEmailAndPassword("jamesClark@gmail.com", "james2023"))
                .thenReturn(Optional.of(user));
        User loggedInUser = userService.loginUser("jamesClark@gmail.com","james2023");
        Assertions.assertThat(loggedInUser).isNotNull();
        Assertions.assertThat(loggedInUser.getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(loggedInUser.getPassword()).isEqualTo(user.getPassword());
    }
}
