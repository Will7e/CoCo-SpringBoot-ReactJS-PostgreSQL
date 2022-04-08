package com.example.coco.service;

import com.example.coco.models.*;
import com.example.coco.security.PasswordEncoder;
import com.example.coco.dao.ConfirmationTokenDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final static String USER_NOT_FOUND = "User with Email: %s not found";
    private  PasswordEncoder passwordEncoder;
    private  ConfirmationTokenDAO confirmationTokenDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userDAO.findUserByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String signUpUser(User user){
        boolean userExist = userDAO
                .findUserByEmail(user.getEmail())
                .isPresent();

        if (userExist && !user.getEnabled()) {
            throw new IllegalStateException("Please activate your account");
        }else if (userExist) {
            throw  new IllegalStateException("Email already taken");
        }

       String encodedPassword = passwordEncoder.bCryptPasswordEncoder()
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.saveUser(user);

        String token = UUID.randomUUID().toString() ;
        //Send confirmation token here

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                user
        );
        confirmationTokenDAO.saveToken(confirmationToken);

        //Send to email

        return token;
    }
    public int enableUser(String email){
        return userDAO.enableUser(email);
    }

    public Optional<User> getUserFirstName(User user){
        return userDAO.findUserById(user.getUserId());

    }

    public long currentUserId(@AuthenticationPrincipal User user ){
        return user.getUserId();

    }

    // Search methods
    public void addSearch(Search search) {
        userDAO.addSearch(search);
    }

    // Interest methods

    //Skill methods
    public List<Skill> getAllSkills() {
        return (List<Skill>) userDAO.getAllSkills();
    }

    public void addSkill(Skill skill) {
        userDAO.addSkill(skill);
    }

    public List<Skill> getMySkills(User user) {
        return user.getSkills();
    }

    /**
     * returns list of searches in the db that user is a match to.
     * @param user
     * @return List of searches
     */
    public List<Search> getMatchingSearches(User user) {
        List<Search> matchingSearches = userDAO.getAllSearches().stream()
                .filter(s->matchUserToSearch(user, s))
                .collect(Collectors.toList());
        return matchingSearches;
    }

    /**
     * returns true if user is a match to search
     * @param user
     * @param search
     * @return boolean match
     */
    private boolean matchUserToSearch(User user, Search search){
        // check if user is open to contacts of SearchType
        List<SearchType> isOpenTo =  user.getOpenForSearchType().stream()
                .filter(o -> o.getId().equals(search.getSearchTypeId()))
                .collect(Collectors.toList());
        if(isOpenTo.size() == 0) return false;
        // check location
        if(!(search.getLocationId() == null || search.getLocationId() == user.getLocation().getId())) return false;
       // check if user have all the skills in the search
        List<Skill> searchedSkills = search.getSearchSkils();
        List<Skill> userSkills = user.getSkills();
        for (Skill searchedSkill:searchedSkills) {
            boolean foundSkill = false;
            for (Skill userSkill:userSkills) {
                if (searchedSkill.getId() == userSkill.getId()) foundSkill = true;
            }
            if(!foundSkill) return false; //if we find one unmatched skill - no match!
        }
        // check if user have all interests in the search
        List<Interest> searchedInterests = search.getSearchInterests();
        List<Interest> userInterests = user.getInterests();
        for (Interest searchedInterest:searchedInterests) {
            boolean foundInterest = false;
            for (Interest userInterest:userInterests) {
                if (searchedInterest.getId() == userInterest.getId()) foundInterest = true;
            }
            if(!foundInterest) return false; //if we find one unmatched skill - no match!
        }
        //if we got this far without returning false, consider it a match!
        return true;
    }

    public List<User> getMatchingUsers(Search search) {
        List<User> matchingUsers = userDAO.getAllUsers().stream()
                .filter(u -> matchUserToSearch(u, search))
                .collect(Collectors.toList());
        return matchingUsers;
    }

    public List<Location> getAllLocations() {
        return userDAO.getAllLocations();
    }

    public void addLocation(Location location) {
        userDAO.addLocation(location);
    }

    public Interest addInterest(Interest interest) {
        return userDAO.addInterest(interest);
    }

    public List<Interest> getAllInterests() {
        return userDAO.getAllInterests();
    }

    public Interest addInterestToUser(long userId, long id) {
        return userDAO.addInterestToUser(userId, id);
    }

    public Location addLocationToUser(long userId, long id) {
        return userDAO.addLocationToUser(userId, id);
    }
}
