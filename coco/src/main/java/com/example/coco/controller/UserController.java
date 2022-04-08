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

    //Interest mappings:
    @GetMapping("/interests/all")
    public List<Interest> getAllInterests() {
        return userService.getAllInterests();
    }

    @PutMapping("/interests/{id}")
    public Interest addInterestToUser(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.addInterestToUser(user.getUserId(), id);
    }

    @PostMapping("/interests/add")
    public Interest addInterest(@RequestBody Interest interest) {
        return userService.addInterest(interest);
    }

    @GetMapping("/interests")
    public List<Interest> getInterestsByUser(@AuthenticationPrincipal User user) {

        return user.getInterests();
    }

    //Location mappings:

    @GetMapping("/location")
    public List<Location> getAllLocations() {
        return userService.getAllLocations();
    }

    @PutMapping("/interests/{id}")
    public Location addLocationToUser(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.addLocationToUser(user.getUserId(), id);
    }
    @PostMapping("/location/add")
    public void addLocation(@RequestBody Location location) {
        userService.addLocation(location);
    }



    //Search methods:

    @PostMapping("/search/add")
    public void addSearch(@RequestBody Search search) {
        userService.addSearch(search);
    }


    // skall kopplas till inloggad användare!
    public List<Search> getMatchingSearches(@AuthenticationPrincipal User user) {
        return userService.getMatchingSearches(user);
    }

    /**
     * @return
     */
    @PostMapping("/search/matches")
    public int searchNoOfMatches(@RequestBody Search search) {
        return userService.getMatchingUsers(search).size();
    }

    //Skill methods:

    @GetMapping("/skills/all")
    /**
     * Returns all skills listed in the db
     */
    public List<Skill> getAllSkills() {
        return userService.getAllSkills();
    }

    /**
     * Adds skill to db.
     */
    @PostMapping("/skills/add")
    public void addSkill(@RequestBody Skill skill) {
        userService.addSkill(skill);
    }

    @GetMapping("/skills")
    public List<Skill> getMySkillNames(@AuthenticationPrincipal User user) {
        return userService.getMySkills(user);
    }


}
