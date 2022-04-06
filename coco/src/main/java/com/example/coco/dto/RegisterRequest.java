package com.example.coco.dto;


import lombok.*;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
}
