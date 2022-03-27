package com.example.coco.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    long userId;
    private String name;
    private String userName;
    private String passwords;
    private String email;
    private String birthYear;
    private String location;
    private String biografy;
    private List<String> hobbies;
    private Instant created;
    private boolean enabled;



}
