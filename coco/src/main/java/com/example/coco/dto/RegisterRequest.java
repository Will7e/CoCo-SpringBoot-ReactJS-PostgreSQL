package com.example.coco.dto;


import lombok.*;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String userName;
    private String passwords;

}
