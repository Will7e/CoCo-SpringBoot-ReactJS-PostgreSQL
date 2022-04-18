package com.example.coco.api;

import com.example.coco.dto.EditRequest;
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

    //Interest Mappings:

    /**
     * Returns all the users interests.
     *
     * @param user User
     * @return List
     */
    @GetMapping("/interests")
    public String getInterestsByUser(@AuthenticationPrincipal User user) {
        if (user == null) return null;
        return userService.getInterestByUser(user);
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
/*
    /**
     * Adds interest with given id to current user
     *
     * @param id   long
     * @param user User
     * @return Interest
     */
    /*
    @PutMapping("/interests/{id}")
    public Interest addInterestToUser(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.addInterestToUser(user.getId(), id);
    }
*/
   /* /**
     * Adds interest to db
     *
     * @param interest Interest
     * @return Interest
     */
    /*@PostMapping("/interests/add")
    public Interest addInterest(@RequestBody Interest interest) {
        return userService.addInterest(interest);
    }

    //Location Mappings:

    @GetMapping("/location")
    public List<Location> getAllLocations() {
        return userService.getAllLocations();
    }
/*
    @PutMapping("/location/{id}")
    public Location setUsersLocation(@PathVariable("id") long id, @AuthenticationPrincipal User user) {
        return userService.setUsersLocation(user.getId(), id);
    }
*/
   /* @PostMapping("/location/add")
    public Location addLocation(@RequestBody Location location) {
        return userService.addLocation(location);
    }
*/

    //Search Mappings:

    @GetMapping("/search/open")
    public List<SearchType> getOpenFor(@AuthenticationPrincipal User user) {
        return userService.openFor(user);
    }

    @PostMapping("/search/add")
    public Search addSearch(@RequestBody Search search) {
        return userService.addSearch(search);
    }

    /*
    @GetMapping("/search")
    public List<Search> getMatchingSearches(@AuthenticationPrincipal User user) {
        return userService.getMatchingSearches(user);
    }

    /**
     * Returns number of users matching the search
     *
     * @param search Search
     * @return int
     */
  /*
    @PostMapping("/search/matches")
    public int searchNoOfMatches(@RequestBody Search search) {
        return userService.getMatchingUsers(search).size();
    }

    @PutMapping("search/{id}")
    public User connectViaSearch(@AuthenticationPrincipal User user, @PathVariable("id") long id) {
        return userService.connectViaSearch(user, id);
    }
    //Skill Mappings:

*/
   /* /**
     * Returns all skills listed in the db
     */
  /*  @GetMapping("/skills/all")
    public List<Skill> getAllSkills() {
        return userService.getAllSkills();
    }

    /**
     * Adds skill to db.
     */


   /* /**
     * Returns all skills of the current user.
     *
     * @param user User
     * @return List
     */
   /* @GetMapping("/skills")
    public List<Skill> getMySkillNames(@AuthenticationPrincipal User user) {
        return userService.getMySkills(user);
    }
*/

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












}
