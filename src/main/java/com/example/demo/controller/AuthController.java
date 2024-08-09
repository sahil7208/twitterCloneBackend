package com.example.demo.controller;

import com.example.demo.exception.UserException;
import com.example.demo.model.Users;
import com.example.demo.model.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import com.example.demo.config.JwtProvider;
import com.example.demo.reponse.AuthResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsServiceImplementation;

import org.springframework.security.core.Authentication;


@RestController
@RequestMapping(path="/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsServiceImplementation customerUserDetails;


    @PostMapping(path="/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody Users user) throws UserException {
        //System.out.println("Hello");
        String email = user.getEmail();
        String fullName = user.getFullName();
        String password = user.getPassword();

        String birthDate = user.getBirthDate();


        Users isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new UserException("Email is already used with another account");
        }

        Users createdUser = new Users();
       // User createdUser;
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVerification(new Verification());

        System.out.println(email);
        System.out.println(fullName);
        System.out.println(passwordEncoder.encode(password));
        System.out.println(birthDate);


        Users savedUser = userRepository.save(createdUser);

//        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

       // String token = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse(email, true);

        //AuthResponse res = new AuthResponse(token, true);



        return new ResponseEntity<AuthResponse>(response,HttpStatus.CREATED);
    }



//    @PostMapping(path="/login")
//    public String postMethodName(@RequestBody String entity) {
//        //TODO: process POST request
//
//        return entity;
//    }
    @PostMapping(path="/login")
    public ResponseEntity<AuthResponse>signin(@RequestBody Users user){
        String username = user.getEmail();
        String password = user.getPassword();

        //Authentication authentication = authenticate(username,password);

       // String token = jwtProvider.generateToken(authentication);


       // AuthResponse res = new AuthResponse(token, true);

        AuthResponse response = new AuthResponse(username, true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String username,String password){
        UserDetails userDetails = customerUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username....");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
throw new BadCredentialsException("Invalid username or password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());


    }
}
