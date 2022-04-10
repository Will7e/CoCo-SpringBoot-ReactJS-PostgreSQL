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
    String searchPresentation, occupation;
    @ManyToMany
    List<Interest> searchInterests;
    @ManyToMany
    List<Skill> searchSkills;

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

    public List<Skill> getSearchSkills() {
        return searchSkills;
    }

    public void setSearchSkills(List<Skill> searchSkils) {
        this.searchSkills = searchSkils;
    }
}
