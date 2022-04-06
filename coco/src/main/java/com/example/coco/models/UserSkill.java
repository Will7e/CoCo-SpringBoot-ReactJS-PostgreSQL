package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "userskills")
public class UserSkill {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xRefId;
    private Integer skillId;
    private Integer userId;

    public UserSkill() {
    }

    public Integer getxRefId() {
        return xRefId;
    }

    public void setxRefId(Integer xRefId) {
        this.xRefId = xRefId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
