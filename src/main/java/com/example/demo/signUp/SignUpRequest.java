package com.example.demo.signUp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignUpRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String DOB;
    private final String password;
    // private final String
}
