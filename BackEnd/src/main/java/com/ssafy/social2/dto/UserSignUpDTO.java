package com.ssafy.social2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpDTO {
    private String email;
    private String password;
    private String nickname;
    private int age;
    private String city;
}
