package com.example.coco.service;

import com.example.coco.models.*;
import com.example.coco.security.PasswordEncoder;
import com.example.coco.dao.ConfirmationTokenDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.dao.UserInterestDAO;
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
    private final UserInterestDAO userInterestDAO;
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
    public List<Interest> getInterestsByUserId(long id) {
        List<UserInterest> userInterests =  userDAO.getAllUserInterests().stream()
                .filter(i -> i.getUserId() == id)
                .collect(Collectors.toList());

        List<Interest> interests = null;
        for (UserInterest userInterest:userInterests) {
            Optional<Interest> maybeInterest = userDAO.getInterestById(userInterest.getInterestId());
            if(maybeInterest.isPresent()){ interests.add(maybeInterest.get());}
        }

        return interests;
    }

    //Skill methods
    public List<Skill> getAllSkills() {
        return (List<Skill>) userDAO.getAllSkills();
    }

    public void addSkill(Skill skill) {
        userDAO.addSkill(skill);
    }

    public List<String> getMySkillNames(long id) {
        List<UserSkill> myUserSkills = userDAO.getAllUserSkills().stream()
                .filter(us -> us.getUserId() == id)
                .collect(Collectors.toList());
        List<Skill> allSkills = (List<Skill>)  userDAO.getAllSkills();
        List<String> mySkills = null;
        for (UserSkill userSkill:myUserSkills) {
            for (Skill skill:allSkills) {
                if(skill.getId() == userSkill.getSkillId()){
                    mySkills.add(skill.getName());
                }
            }

        }
        return mySkills;
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
        List<OpenForSearchType> isOpenTo =  userDAO.getIsOpenTo(user.getUserId()).stream()
                .filter(o -> o.getUserId() == user.getUserId())
                .filter(o -> o.getSearchTypeId().equals(search.getSearchTypeId()))
                .collect(Collectors.toList());
        if(isOpenTo.size() == 0) return false;
        // check location
        if(!(search.getLocationId() == null || search.getLocationId() == user.getLocation().getId())) return false;
       // check if user have all the skills in the search
        List<SearchSkill> searchedSkills = userDAO.getSkillsInSearches().stream()
                .filter(s -> s.getSearchId() == search.getSearchId())
                .collect(Collectors.toList());
        List<UserSkill> userSkills = userDAO.getAllUserSkills().stream()
                .filter(s -> s.getUserId() == user.getUserId())
                .collect(Collectors.toList());
        for (SearchSkill searchedSkill:searchedSkills) {
            boolean foundSkill = false;
            for (UserSkill userSkill:userSkills) {
                if (searchedSkill.getSkillId() == userSkill.getSkillId()) foundSkill = true;
            }
            if(!foundSkill) return false; //if we find one unmatched skill - no match!
        }
        // check if user have all interests in the search
        List<SearchInterest> searchedInterests = userDAO.getInterestsInSearches().stream()
                .filter(i -> i.getSearchId() == search.getSearchId())
                .collect(Collectors.toList());
        List<UserInterest> userInterests = userDAO.getAllUserInterests().stream()
                .filter(i -> i.getUserId() == user.getUserId())
                .collect(Collectors.toList());
        for (SearchInterest searchedInterest:searchedInterests) {
            boolean foundInterest = false;
            for (UserInterest userInterest:userInterests) {
                if (searchedInterest.getInterestId().equals(userInterest.getInterestId())) foundInterest = true;
            }
            if(!foundInterest) return false; //if we find one unmatched interest - no match!
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
}
