package com.example.coco.service;

import com.example.coco.dao.InterestDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.dao.UserInterestDAO;
import com.example.coco.models.Interest;
import com.example.coco.models.Skill;
import com.example.coco.models.UserInterest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final InterestDAO interestDAO;
    private final UserInterestDAO userInterestDAO;
    private final static String USER_NOT_FOUND = "User with Email: %s not found";


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userDAO.findUserByEmail(email);
    }

    // Interest methods
    public List<Interest> getInterestsByUserId(long id) {
        List<UserInterest> userInterests =  userInterestDAO.getUserInterests().stream()
                .filter(i -> i.getUserId() == id)
                .collect(Collectors.toList());

        List<Interest> interests = null;
        for (UserInterest userInterest:userInterests) {
            Optional<Interest> maybeInterest = interestDAO.getInterestById(userInterest.getInterestId());
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

    public List<String> getMySkills(long id) {
        List<Skill> skills = userDAO.getSkillsByUser(id);
    }
}
