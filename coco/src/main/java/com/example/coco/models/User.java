package com.example.coco.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    long id;
    String name;
    String email;
    String passwords;
    String birthYear;
    String location;
    String biografy;
    List<String> hobbies;

    public User(long id, String name, String email, String passwords, String birthYear, String location, String biografy) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwords = passwords;
        this.birthYear = birthYear;
        this.location = location;
        this.biografy = biografy;
        hobbies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBiografy() {
        return biografy;
    }

    public void setBiografy(String biografy) {
        this.biografy = biografy;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
