package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "searchskills")
public class SearchSkill {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xRefId;
    private Integer searchId;
    private Integer skillId;


    public SearchSkill() {
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

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }
}
