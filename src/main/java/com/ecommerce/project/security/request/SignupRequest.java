package com.ecommerce.project.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


@Data
public class SignupRequest {

    @NotBlank
    @Size(min = 3,max=20,message = "username should be at least 3 character long")
    private String username;

    @NotBlank
    @Size(max=50)
    @Email
    private String email;



    private Set<String> role;


    @NotBlank
    @Size(min = 6,max=40,message = "password should be at least 6 characters long")
    private String password;
}
