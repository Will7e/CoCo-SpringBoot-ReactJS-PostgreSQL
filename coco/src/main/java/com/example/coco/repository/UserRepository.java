package com.example.coco.repository;

import com.example.coco.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByName(String username);
    Optional<User> findUserByEmail(String email);
}

