package com.example.coco.dto;


import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String userName;
    private String passwords;

}
