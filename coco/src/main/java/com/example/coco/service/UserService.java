package com.example.coco.service;

import com.example.coco.dao.InterestDAO;
import com.example.coco.security.PasswordEncoder;
import com.example.coco.dao.ConfirmationTokenDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.dao.UserInterestDAO;
import com.example.coco.models.Interest;
import com.example.coco.models.Skill;
import com.example.coco.models.UserInterest;
import com.example.coco.models.User;
import com.example.coco.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final InterestDAO interestDAO;
    private final UserInterestDAO userInterestDAO;
    private final static String USER_NOT_FOUND = "User with Email: %s not found";

public class  UserService implements UserDetailsService {
    private UserDAO userDAO;
    private  PasswordEncoder passwordEncoder;
    private static String USER_NOT_FOUND = "User with Email: %s not found";
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
