package com.example.coco.controller;

import com.example.coco.models.*;
import com.example.coco.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public long userLoggedId(User user) {
        return userService.currentUserId(user);
    }

    //Interest Mappings:

    /**
     * Returns all the users interests.
     * @param user User
     * @return List
     */
    @GetMapping("/interests")
    public List<Interest> getInterestsByUser(@AuthenticationPrincipal User user) {
        if (user == null) return null;
        return userService.getInterestByUser(user);
    }

    /**
     * Returns all interests in the db
     * @return List
     */
    @GetMapping("/interests/all")
    public List<Interest> getAllInterests() {
        return userService.getAllInterests();
    }

    /**
     * Adds interest with given id to current user
     * @param id long
     * @param user User
     * @return Interest
     */
    @PutMapping("/interests/{id}")
    public Interest addInterestToUser(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.addInterestToUser(user.getUserId(), id);
    }

    /**
     * Adds interest to db
     * @param interest Interest
     * @return Interest
     */
    @PostMapping("/interests/add")
    public Interest addInterest(@RequestBody Interest interest) {
        return userService.addInterest(interest);
    }


    //Location Mappings:

    @GetMapping("/location")
    public List<Location> getAllLocations() {
        return userService.getAllLocations();
    }

    @PutMapping("/location/{id}")
    public Location setUsersLocation(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.setUsersLocation(user.getUserId(), id);
    }

    @PostMapping("/location/add")
    public Location addLocation(@RequestBody Location location) {
        return userService.addLocation(location);
    }


    //Search Mappings:

    @PostMapping("/search/add")
    public void addSearch(@RequestBody Search search) {
        userService.addSearch(search);
    }

    @GetMapping("/search")
    public List<Search> getMatchingSearches(@AuthenticationPrincipal User user) {
        return userService.getMatchingSearches(user);
    }

    /**
     * Returns number of users matching the search
     * @param search Search
     * @return int
     */
    @PostMapping("/search/matches")
    public int searchNoOfMatches(@RequestBody Search search) {
        return userService.getMatchingUsers(search).size();
    }

    //Skill Mappings:


    /**
     * Returns all skills listed in the db
     */
    @GetMapping("/skills/all")
    public List<Skill> getAllSkills() {
        return userService.getAllSkills();
    }

    /**
     * Adds skill to db.
     */
    @PostMapping("/skills/add")
    public Skill addSkill(@RequestBody Skill skill) {
        return userService.addSkill(skill);
    }

    /**
     * Returns all skills of the current user.
     * @param user User
     * @return List
     */
    @GetMapping("/skills")
    public List<Skill> getMySkillNames(@AuthenticationPrincipal User user) {
        return userService.getMySkills(user);
    }


}
