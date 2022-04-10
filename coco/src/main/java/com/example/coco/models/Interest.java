package com.example.coco.models;

import javax.persistence.*;

@Entity
@Table(name="interests")
public class Interest {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    public Interest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer interestId) {
        this.id = interestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
