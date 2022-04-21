package com.example.coco.dao;

import com.example.coco.models.*;
import com.example.coco.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAO {
    UserRepository userRepository;
    SkillRepository skillRepository;
    InterestRepository interestRepository;
    SearchRepository searchRepository;
    LocationRepository locationRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

   public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findCurrentUserById(long id){
        return userRepository.findUserById(id);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Skill getSkillById(Integer id) {
        return skillRepository.findSkillById(id);
    }


}
