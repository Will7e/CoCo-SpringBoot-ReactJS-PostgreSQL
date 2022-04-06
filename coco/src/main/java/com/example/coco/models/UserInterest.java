package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "userinterests")
public class UserInterest {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xRefId;
    private Long userId;
    private Long interestId;

    public UserInterest() {
    }

    public Long getxRefId() {
        return xRefId;
    }

    public void setxRefId(Long xRefId) {
        this.xRefId = xRefId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }
}
