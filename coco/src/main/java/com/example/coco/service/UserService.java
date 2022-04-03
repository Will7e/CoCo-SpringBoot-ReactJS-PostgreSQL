package com.example.coco.service;

import com.example.coco.config.PasswordEncoder;
import com.example.coco.dao.ConfirmationTokenDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.models.User;
import com.example.coco.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class  UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND = "User with Email: %s not found";
    private final ConfirmationTokenDAO confirmationTokenDAO;
    private final EmailService emailService;


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
}
