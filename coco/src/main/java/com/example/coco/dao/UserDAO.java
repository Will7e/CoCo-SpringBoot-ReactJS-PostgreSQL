package com.example.coco.dao;

import com.example.coco.models.*;
import com.example.coco.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAO {
    UserRepository userRepository;
    SkillRepository skillRepository;
    UserSkillRepository userSkillRepository;
    InterestRepository interestRepository;
    UserInterestRepository userInterestRepository;
    SearchRepository searchRepository;
    SearchSkillRepository searchSkillRepository;
    SearchInterestRepository searchInterestRepository;
    OpenForSearchTypeRepository openForSearchTypeRepository;
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


    //Skill Methods:
    public Iterable<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public List<UserSkill> getAllUserSkills() {
        return (List<UserSkill>) userSkillRepository.findAll();
    }

    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }


    public void addSearch(Search search) {
        searchRepository.save(search);
    }

    public List<Search> getAllSearches() {
        return (List<Search>) searchRepository.findAll();
    }

    public List<OpenForSearchType> getIsOpenTo(long userId) {
        return (List<OpenForSearchType>) openForSearchTypeRepository.findAll();
    }

    public List<SearchSkill> getSkillsInSearches() {
        return (List<SearchSkill> )searchSkillRepository.findAll();
    }

    public List<SearchInterest> getInterestsInSearches() {
        return (List<SearchInterest>) searchInterestRepository.findAll();
    }

    public List<UserInterest> getAllUserInterests() {
        return (List<UserInterest>) userInterestRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Location> getAllLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }



    public List<UserInterest> getUserInterests() {
        return (List<UserInterest>) userInterestRepository.findAll();
    }

    public Optional<Interest> getInterestById(Long interestId) {
        return interestRepository.findById(interestId);
    }
}
