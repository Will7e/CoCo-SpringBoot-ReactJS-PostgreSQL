package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer xRefId;
    private Integer userId;
    private Integer contactId;
    private Integer searchId;

    public Contact() {
    }

    public Integer getxRefId() {
        return xRefId;
    }

    public void setxRefId(Integer xRefId) {
        this.xRefId = xRefId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }
}
