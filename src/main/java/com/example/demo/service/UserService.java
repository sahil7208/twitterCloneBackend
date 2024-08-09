package com.example.demo.service;

import com.example.demo.exception.UserException;
import com.example.demo.model.Users;

import java.util.List;


public interface UserService {

    public Users findUserById(Long userId) throws UserException;

    public Users findUserprofileByJwt(String email1) throws UserException;

    public Users updateUser(Long userId, Users users) throws  UserException;

    public Users followUser(Long userId, Users users) throws UserException;

    public List<Users> searchUser(String query);


}
