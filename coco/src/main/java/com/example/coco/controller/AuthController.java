package com.example.coco.controller;


import com.example.coco.dto.LoginRequest;
import com.example.coco.dto.RegisterRequest;
import com.example.coco.models.User;
import com.example.coco.response.UserInfoResponse;
import com.example.coco.security.JwtUtils;
import com.example.coco.service.AuthService;
import com.example.coco.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public String signUp(@RequestBody RegisterRequest registerRequest){
      return authService.signUp(registerRequest);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return authService.confirmToken(token);
    }

    @GetMapping("/signin")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(user.getUserId(),
                        user.getFullName(),
                        user.getEmail(),
                        roles));

        return "It works";
    }
}
