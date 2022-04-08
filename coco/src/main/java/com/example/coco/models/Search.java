package com.example.coco.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "searches")
public class Search {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long searchId;
    Long userId, searchTypeId, locationId;
    Date birthYear;
    String searchPresentation, occupation;
    @ManyToMany
    List<Interest> searchInterests;
    @ManyToMany
    List<Skill> searchSkils;

    public Search() {
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSearchTypeId() {
        return searchTypeId;
    }

    public void setSearchTypeId(Long searchTypeId) {
        this.searchTypeId = searchTypeId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getSearchPresentation() {
        return searchPresentation;
    }

    public void setSearchPresentation(String searchPresentation) {
        this.searchPresentation = searchPresentation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Interest> getSearchInterests() {
        return searchInterests;
    }

    public void setSearchInterests(List<Interest> searchInterests) {
        this.searchInterests = searchInterests;
    }

    public List<Skill> getSearchSkils() {
        return searchSkils;
    }

    public void setSearchSkils(List<Skill> searchSkils) {
        this.searchSkils = searchSkils;
    }
}
