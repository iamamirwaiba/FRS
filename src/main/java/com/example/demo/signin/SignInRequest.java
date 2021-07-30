package com.example.demo.signin;


import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SignInRequest {
    private final String phoneNumber;
    private final  String password;
}
