package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "userskills")
public class UserSkill {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xRefId;
    private Integer id;
    private Integer searchId;

    public UserSkill() {
    }

    public Integer getxRefId() {
        return xRefId;
    }

    public void setxRefId(Integer xRefId) {
        this.xRefId = xRefId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }
}
