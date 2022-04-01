package com.example.coco.dao;

import com.example.coco.models.Interest;
import com.example.coco.models.UserInterest;
import com.example.coco.repository.InterestRepository;
import com.example.coco.repository.UserInterestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInterestDAO {
    UserInterestRepository repository;

    public UserInterestDAO(UserInterestRepository repository) {
        this.repository = repository;
    }

    public List<UserInterest> getUserInterests() {
        return (List<UserInterest>) repository.findAll();
    }

}

