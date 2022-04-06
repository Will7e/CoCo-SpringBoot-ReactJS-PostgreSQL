package com.example.coco.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "userinterests")
public class OpenForSearchType {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xRefId;
    private Long userId;
    private Long searchTypeId;

    public OpenForSearchType() {
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

    public Long getSearchTypeId() {
        return searchTypeId;
    }

    public void setSearchTypeId(Long searchTypeId) {
        this.searchTypeId = searchTypeId;
    }
}

