package com.example.coco.api;

import com.example.coco.dto.EditRequest;
import com.example.coco.dto.FriendRequest;
import com.example.coco.dto.SkillRequest;
import com.example.coco.models.*;
import com.example.coco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }



    // Add skill to user (Works on postman)
    @PutMapping("/skills/add")
    @PreAuthorize("hasRole('USER')")
    public String addSkillsToUser(@Valid @RequestBody SkillRequest skillRequest){
        return userService.addSkillsToUSer(skillRequest);
    }

    // Remove skill from user (Works on postman)
    @DeleteMapping("/skills/remove")
    @PreAuthorize("hasRole('USER')")
    public String removeUserSkill(@Valid @RequestBody SkillRequest skillRequest){
        return userService.removeUserSkill(skillRequest);
    }

    // Find all user with by skill id ("Works on postman")
    // Works on web
    @GetMapping("/skills/user/{skillId}")
    public List<User> findAllUserBySkills(@PathVariable ("skillId") Integer skillId){
        return userService.findAllUserBySkills(skillId);

    }

    //Edit user information ( works on postman)/
    // Works now on web
    @PutMapping("/edit/user")
    public String editUser(@Valid @RequestBody EditRequest editRequest){
        return userService.editUser(editRequest);
    }


    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @PostMapping("/friends/add")
    public String addFriend(@Valid @RequestBody FriendRequest friendRequest){
        return userService.addFriend(friendRequest);
    }

    @GetMapping("/friends/{id}")
    public List<User> getFriendList(@PathVariable ("id") long id){
        return userService.getFriendList(id);
    }

    @DeleteMapping("/friends/delete")
    public String unfriend(@Valid @RequestBody FriendRequest friendRequest){
        return userService.unfriend(friendRequest);
    }





}
