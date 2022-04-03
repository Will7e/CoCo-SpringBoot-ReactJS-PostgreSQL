package com.example.coco.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {



    @GetMapping("/{id}")
    public String userPage(@RequestParam("id") UUID uuid){

        return "";
    }
}

