package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "searchinterests")
public class SearchInterest {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xRefId;
    private Integer searchId;
    private Integer interestId;


    public SearchInterest() {
    }

    public Integer getxRefId() {
        return xRefId;
    }

    public void setxRefId(Integer xRefId) {
        this.xRefId = xRefId;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }
}
