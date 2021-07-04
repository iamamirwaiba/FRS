package com.example.demo.signUp;

import com.example.demo.signUp.SignUpRequest;
import com.example.demo.signUp.SignUpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/signUp")
@AllArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public String register(@RequestBody SignUpRequest request) {
        return signUpService.register(request);
    }

}
