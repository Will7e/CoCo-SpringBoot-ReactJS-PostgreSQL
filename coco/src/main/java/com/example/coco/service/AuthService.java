package com.example.coco.service;

import com.example.coco.config.SecurityConfig;
import com.example.coco.dao.UserDAO;
import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {
    UserDAO userDAO;
    SecurityConfig securityConfig;



    public String signUp(RegisterRequest registerRequest){
        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setUserName(registerRequest.getUserName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPasswords(registerRequest.getPasswords());


        userDAO.saveUser(newUser);

        return  "It works";
    }

}
