package com.example.coco.service;

import com.example.coco.dao.UserDAO;
import com.example.coco.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final static String USER_NOT_FOUND = "User with Email: %s not found";


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userDAO.findUserByEmail(email);
    }
}
