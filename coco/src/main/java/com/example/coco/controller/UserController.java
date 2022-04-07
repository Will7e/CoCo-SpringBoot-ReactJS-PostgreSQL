package com.example.coco.controller;


import com.example.coco.models.*;
import com.example.coco.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coco.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public long userLoggedId(User user){
        return userService.currentUserId(user);
    }


    @GetMapping("/interests/{id}")
    public List<Interest> getInterestsByUserId(@PathVariable("id") long id){
        return userService.getInterestsByUserId(id);
    }

    //Location mappings:
    @GetMapping("/location/all")
    public List<Location> getAllLocations(){
        return userService.getAllLocations();
    }

    @PostMapping("/location/add")
    public void addLocation(@RequestBody Location location){
        userService.addLocation(location);
    }
    //Search methods:

    @PostMapping("/search/add")
    public void addSearch(@RequestBody Search search){
        userService.addSearch(search);
    }


    // skall kopplas till inloggad användare!
    public List<Search> getMatchingSearches(User user){
        return userService.getMatchingSearches(user);
    }

    /**
     *
     * @return
     */
    @PostMapping("/search/matches")
    public int searchNoOfMatches(@RequestBody Search search){
        return userService.getMatchingUsers(search).size();
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

    @GetMapping("/skills/{id}")
    public List<String> getMySkillNames(@PathVariable("id") long id){
        return userService.getMySkillNames(id);
    }




}
