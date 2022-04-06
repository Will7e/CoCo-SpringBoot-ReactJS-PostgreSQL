package com.example.coco.api;


import com.example.coco.models.User;
import com.example.coco.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("home")
@AllArgsConstructor
public class UserController {
    UserService userService;


    @GetMapping()
    public long userLoggedId(User user){
        return userService.currentUserId(user);
    }

    @GetMapping("/user")
    public List<User> userList (User user){
        return userService.findAllUser(user);
    }

}

