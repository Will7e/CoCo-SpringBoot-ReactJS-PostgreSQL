package com.example.coco.controller;


import com.example.coco.models.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("home")
@AllArgsConstructor
public class UserController {

    @GetMapping()
    public String userPage(){

        return "Hello there";
    }


}

