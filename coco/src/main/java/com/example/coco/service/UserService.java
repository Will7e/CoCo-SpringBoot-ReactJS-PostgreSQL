package com.example.coco.service;

import com.example.coco.dao.UserDAO;
import com.example.coco.dto.EditRequest;
import com.example.coco.dto.FriendRequest;
import com.example.coco.dto.SkillRequest;
import com.example.coco.models.*;
import com.example.coco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    UserDAO userDAO;

    public UserService (UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return PrincipalUser.build(user);
    }

    public Optional<User> getUserFirstName(User user) {
        return userDAO.findUserById(user.getId());
    }

    public User getCurrentUser() {

        PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userDAO.findUserById(principalUser.getId());
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }


    public String addSkillsToUSer(SkillRequest skillRequest) {
        Skill skill = userDAO.getSkillById(skillRequest.getSkillId());
        if (skill == null) {
            return "Skill not found";
        }

        User user = userDAO.findCurrentUserById(skillRequest.getUserId());
        if (user == null) {
            return "User not found";
        }

        List<Skill> userSkills = user.getSkills();

        for (Skill skill1 : userSkills) {
            if (skill1 == skill) {
                return "Skill already exist in ur list";
            }

        }
        userSkills.add(skill);
        user.setSkills(userSkills);
        userDAO.saveUser(user);

        return "Skill added";

    }

    public String removeUserSkill(SkillRequest skillRequest) {
        Skill skill = userDAO.getSkillById(skillRequest.getSkillId());
        if (skill == null) {
            return "Skill does not exist";
        }
        User user = userDAO.findCurrentUserById(skillRequest.getUserId());
        if (user == null) {
            return "User does not exist";
        }

        List<Skill> userSkills = user.getSkills();

        for (int i = 0; i < userSkills.size(); i++){
            if  (!userSkills.contains(skill)){
                return "Skill does not exist in list";
            }
        }

        userSkills.remove(skill);
        user.setSkills(userSkills);
        userDAO.saveUser(user);

        return "Skill removed";
    }

    public List<User> findAllUserBySkills(Integer skillId) {
        List<User> newList = new ArrayList<>();

        List<User> userList = userDAO.getAllUsers();

        Skill skill = userDAO.getSkillById(skillId);

        if (userList.isEmpty()){
            return newList;
        }
        for (User user : userList){
            if (user.getSkills().contains(skill)){
                newList.add(user);
            }
        }

        return newList;
    }

    public String editUser(EditRequest editRequest) {
        String message = "Edit success";
        User user = userDAO.findCurrentUserById(editRequest.getUserId());

        if (user == null) {
            return "User not found";
        }
        switch (editRequest.getEditCase()) {

            case 1: user.setFullName(editRequest.getFullName());
                userDAO.saveUser(user);
                break;


            case 2: user.setInterests(editRequest.getInterest());
                userDAO.saveUser(user);
                break;


            case 3: user.setCountry(editRequest.getCountry());
                userDAO.saveUser(user);
                break;


            case 4: user.setCity(editRequest.getCity());
                userDAO.saveUser(user);
                break;


            case 5: user.setContacts(editRequest.getContacts());
                userDAO.saveUser(user);
                break;


            case 6: user.setOccupation(editRequest.getOccupation());
                userDAO.saveUser(user);
                break;

            case 7: user.setPresentation(editRequest.getPresentation());
                userDAO.saveUser(user);
                break;

                default: return "Error";


        }
        return message;

    }

    public List<User> getAllUser() {
        return userDAO.getAllUsers();
    }

    public String addFriend(FriendRequest friendRequest) {
        User user = userDAO.findCurrentUserById(friendRequest.getUserId());
        if (user == null){
            return "Cant find user";
        }
        User friend = userDAO.findCurrentUserById(friendRequest.getFriendId());

        if (friend == null){
            return "Cant find friend";
        }

        if (!user.getFriendList().contains(friend)){
            user.getFriendList().add(friend);
            userDAO.saveUser(user);
            return "User added";
        }

        return "User is already friend";


    }

    public List<User> getFriendList(long id) {
        User user = userDAO.findCurrentUserById(id);

        return user.getFriendList();


    }

    public String unfriend(FriendRequest friendRequest) {
        User user = userDAO.findCurrentUserById(friendRequest.getUserId());
        if (user == null){
            return "Cant find user";
        }
        User friend = userDAO.findCurrentUserById(friendRequest.getFriendId());

        if (friend == null){
            return "Cant find friend";
        }
        if  (user.getFriendList().contains(friend)){
            user.getFriendList().remove(friend);
            userDAO.saveUser(user);
            return "Removed";
        }

        return "Not removed";
    }



}

