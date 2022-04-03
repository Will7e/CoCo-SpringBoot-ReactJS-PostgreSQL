package com.example.coco.service;

import com.example.coco.dao.ConfirmationTokenDAO;
import com.example.coco.dao.UserDAO;
import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import com.example.coco.models.UserRole;
import com.example.coco.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class AuthService {
    UserService userService;
    UserDAO userDAO;
    EmailValidationService emailValidationService; 
    ConfirmationTokenDAO confirmationTokenDAO;
    EmailService emailService;



    public String signUp(RegisterRequest registerRequest){
        boolean isValidEmail = emailValidationService
                .test(registerRequest.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("Email is not valid");
        }
        String token = userService.signUpUser(new User(registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                UserRole.USER
        ));
        String link = "http://localhost:8080/api/auth/confirm?token=" + token;
        emailService.sendEmail(registerRequest.getEmail(),emailService.buildEmail(registerRequest.getFirstName(),link));
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken tokenToConfirm =confirmationTokenDAO
                .findByToken(token)
                .orElseThrow( ()-> new IllegalStateException("Token not found"));

        if (tokenToConfirm.getConfirmAt() != null){
            throw new IllegalStateException ("Email already confirmed");
        }
        LocalDateTime expiredAt = tokenToConfirm.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired");
        }
        confirmationTokenDAO.updateConfirmedToken(token,LocalDateTime.now());

        userService.enableUser(
                tokenToConfirm.getUser().getEmail()
        );

        return "confirmed";
    }



}
