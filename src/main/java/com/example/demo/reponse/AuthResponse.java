package com.example.demo.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private  boolean status;
   // private String message;

    public AuthResponse(String jwt, boolean status) {
        this.jwt = jwt;
        this.status = status;
    }
}
