package com.example.coco.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditRequest {

    private long userId;
    private String fullName;
    private String occupation;
    private String interest;
    private String contacts;
    private String city;
    private String country;
    private String presentation;
}
