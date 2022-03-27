package com.example.coco.controller;


import com.example.coco.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/auth")
public class AuthController {


    @PostMapping("/signup")
    public void signUp(@RequestBody RegisterRequest registerRequest){


    }
}
