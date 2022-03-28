package com.example.coco.service;

import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import com.example.coco.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    public String signUp(RegisterRequest registerRequest){

        return  "It works";
    }

}
