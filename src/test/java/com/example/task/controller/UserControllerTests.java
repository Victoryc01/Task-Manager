package com.example.task.controller;

import com.example.task.entity.User;
import com.example.task.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;



    @Test
    public void checkIfUserControllerGetsRegistrationPage() throws Exception{
        mockMvc.perform(get("/registerUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("userRegistration"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("registerRequest"));
    }
    @Test
    public void checkIfUserControllerRegistersAUserToADatabase() throws Exception{
        User user = User.builder()
                .fullName("James Clark")
                .email("jamesClark@gmail.com").password("james2023").build();

        when(userService.registerUser(anyString(),anyString(),anyString())).thenReturn(user);
        mockMvc.perform(post("/registerUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("fullName=James Clark&email=jamesClark@gmail.com&password=james2023"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registrationSuccessful"));

    }
}
