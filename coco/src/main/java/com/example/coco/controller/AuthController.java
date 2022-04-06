package com.example.coco.controller;


import com.example.coco.dto.LoginRequest;
import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import com.example.coco.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public String signUp(@RequestBody RegisterRequest registerRequest){
      return authService.signUp(registerRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return authService.confirmToken(token);
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication );

        return "success";
    }
}
