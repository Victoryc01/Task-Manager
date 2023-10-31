package com.example.task.service;

import com.example.task.entity.User;
import com.example.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public class UserService {
    private UserRepository userRepository;
     @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String fullName, String email, String password){
         if (email == null || password == null){
             throw new InputMismatchException("invalid input");
         }
         else{
             if (userRepository.findFirstByEmail(email).isPresent()){
                 throw new InputMismatchException("this user already exists");
             }

                 User user = new User();
                 user.setFullName(fullName);
                 user.setEmail(email);
                 user.setPassword(password);
                 userRepository.save(user);
                 return user;

         }
    }

    public User loginUser (String email, String password){
         return userRepository.findUserByEmailAndPassword(email,password).orElse(null);
    }
}
