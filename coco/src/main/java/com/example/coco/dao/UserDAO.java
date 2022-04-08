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

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public int enableUser(String email) {
        return userRepository.enableAppUser(email);
    }

    // Interest Methods:

    public Interest addInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public Interest addInterestToUser(long userId, long id) {
        Optional<User> maybeUser = userRepository.findById(userId);
        Optional<Interest> maybeInterest = interestRepository.findById(id);
        if (maybeInterest.isEmpty() || maybeUser.isEmpty()) return null;

        User user = maybeUser.get();
        Interest interest = maybeInterest.get();
        List<Interest> interests = user.getInterests();
        interests.add(interest);
        user.setInterests(interests);
        return interest;
    }

    // Location Methods:

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    // Search Methods:

    public void addSearch(Search search) {
        searchRepository.save(search);
    }

    public List<Search> getAllSearches() {
        return searchRepository.findAll();
    }

    // Skill Methods:

    public Iterable<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // User Methods

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }




    public Location setUsersLocation(long userId, long id) {

        Optional<User> maybeUser = userRepository.findById(userId);
        Optional<Location> maybeLocation = locationRepository.findById(id);
        if (maybeLocation.isEmpty() || maybeUser.isEmpty()) return null;

        User user = maybeUser.get();
        Location location = maybeLocation.get();
        user.setLocation(location);
        return location;
    }
}
