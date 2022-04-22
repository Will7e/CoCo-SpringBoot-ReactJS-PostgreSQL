package com.example.coco.controller;

import com.example.coco.models.*;
import com.example.coco.service.PrincipalUser;
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

    @GetMapping
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    //Interest Mappings:

    /**
     * Returns all the users interests.
     *
     * @return List
     */
    @GetMapping("/interests")
    public List<Interest> getInterestsByUser() {
        return userService.getInterestByUser(getCurrentUser());
    }

    /**
     * Returns all interests in the db
     *
     * @return List
     */
    @GetMapping("/interests/all")
    public List<Interest> getAllInterests() {
        return userService.getAllInterests();
    }

    /**
     * Adds interest with given id to current user
     *
     * @param id long
     * @return Interest
     */
    @PutMapping("/interests/{id}")
    public Interest addInterestToUser(@PathVariable("id") long id) {
        return userService.addInterestToUser(getCurrentUser().getId(), id);
    }

    /**
     * Adds interest to db
     *
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
    public Location setUsersLocation(@PathVariable("id") long id) {
        return userService.setUsersLocation(getCurrentUser().getId(), id);
    }

    @PostMapping("/location/add")
    public Location addLocation(@RequestBody Location location) {
        return userService.addLocation(location);
    }

    @GetMapping("/location/my")
    public Location getLocation() {
        return userService.getLocation(getCurrentUser());
    }


    //SearchType Mappings:

    @GetMapping("/search/open")
    public List<SearchType> getOpenFor() {
        return userService.openFor(getCurrentUser());
    }

    @GetMapping("/search/types")
    public List<SearchType> getSearchTypes() {
        return userService.getSearchTypes();
    }

    @PutMapping("/search/add-open/{id}")
    public List<SearchType> addOpenFor(@PathVariable("id") long id){
        return userService.addOpenFor(getCurrentUser(), id);
    }

    @PostMapping("/search/addtype")
    public SearchType addSearchType(@RequestBody SearchType searchType) {
        return userService.addSearchType(searchType);
    }

    //SearchType Mappings:

    @PostMapping("/search/add")
    public Search addSearch(@RequestBody Search search) {
        return userService.addSearch(search);
    }

    @GetMapping("/search")
    public List<Search> getMatchingSearches() {
        return userService.getMatchingSearches(getCurrentUser());
    }

    /**
     * Returns number of users matching the search
     *
     * @param search Search
     * @return int
     */
    @PostMapping("/search/matches")
    public int searchNoOfMatches(@RequestBody Search search) {
        return userService.getMatchingUsers(search).size();
    }

    @PutMapping("search/{id}")
    public User connectViaSearch(@PathVariable("id") long id) {
        return userService.connectViaSearch(getCurrentUser(), id);
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
     *
     * @return List
     */
    @GetMapping("/skills")
    public List<Skill> getMySkills() {
        return userService.getMySkills(getCurrentUser());
    }

    /**
     * Adds skill with given id to current user
     *
     * @param id   long
     * @return Interest
     */
    @PutMapping("/skills/{id}")
    public Skill addSkillToUser(@PathVariable("id") long id) {
        return userService.addSkillToUser(getCurrentUser(), id);
    }


}
