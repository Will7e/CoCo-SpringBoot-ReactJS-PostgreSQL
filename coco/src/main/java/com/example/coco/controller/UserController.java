package com.example.coco.controller;


import com.example.coco.models.User;
import com.example.coco.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
@AllArgsConstructor
public class UserController {
    UserService userService;


    @GetMapping()
    public long userLoggedId(User user){
        return userService.currentUserId(user);
    }
}

import com.example.coco.models.Interest;
import com.example.coco.models.Skill;
import com.example.coco.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/interests/{id}")
    public List<Interest> getInterestsByUserId(@PathVariable("id") long id){
        return userService.getInterestsByUserId(id);
    }

    //Skill methods:

    @GetMapping("/skills/all")
    /**
     * Returns all skills listed in the db
     */
    public List<Skill> getAllSkills(){
        return userService.getAllSkills();
    }

    /**
     * Adds skill to db.
     */
    @PostMapping("/skills/add")
    public void addSkill(@RequestBody Skill skill){
        userService.addSkill(skill);
    }

    public List<String> mySkills(long id){
        return userService.getMySkills(id);
    }




}
