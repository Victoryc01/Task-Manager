package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;
     @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registerUser")
    public String getUserRegistrationPage(Model model){
        model.addAttribute("registerRequest",new User());
        return "userRegistration";
    }


    @GetMapping("/userLogin")
    public String getLoginPage(Model model){
      model.addAttribute("loginRequest",new User());
      return "userLogin";
    }



    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute User user){
      User registeredUser =  userService.registerUser(user.getFullName(),
              user.getEmail(),
              user.getPassword());
      return registeredUser == null ? "errorPage" : "registrationSuccessful";
    }



    @PostMapping("/userLogin")
    public String logUserIn(@ModelAttribute User user, Model model, HttpSession session){
         User loggedInUser = userService.loginUser(user.getEmail(),user.getPassword());
         if (loggedInUser != null){
             model.addAttribute("loginRequest",loggedInUser.getEmail());
             session.setAttribute("userId",loggedInUser.getId());
             return "redirect:/creatingTask";
         }else{
             return "errorPage";
         }
    }
}
