package com.example.coco.service;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidationService implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
