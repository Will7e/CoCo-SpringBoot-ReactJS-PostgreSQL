package com.example.coco.controller;


import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import com.example.coco.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public String signUp(@RequestBody RegisterRequest registerRequest){
      return authService.signUp(registerRequest);
    }
}
