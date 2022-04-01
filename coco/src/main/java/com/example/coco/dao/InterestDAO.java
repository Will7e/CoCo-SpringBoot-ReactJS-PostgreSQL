package com.example.coco.dao;

import com.example.coco.models.Interest;
import com.example.coco.repository.InterestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class InterestDAO {
    InterestRepository interestRepository;


    public List<Interest> getInterests() {
        return (List<Interest>) interestRepository.findAll();
    }

    public Optional<Interest> getInterestById(long interestId) {
        return interestRepository.findById(interestId);
    }
}
