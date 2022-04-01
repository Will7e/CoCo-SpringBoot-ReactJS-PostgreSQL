package com.example.coco.dao;

import com.example.coco.models.Interest;
import com.example.coco.models.Skill;
import com.example.coco.models.User;
import com.example.coco.repository.SkillRepository;
import com.example.coco.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAO {
    UserRepository userRepository;
    SkillRepository skillRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

   public List<User> findUserByEmail(String email) {
        return (List<User>) userRepository.findAll();
    }

    //Skill Methods:
    public Iterable<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public List<Skill> getSkillsByUser(long id) {

    }
}
