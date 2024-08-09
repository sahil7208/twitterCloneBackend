package com.example.demo.service;

import com.example.demo.exception.UserException;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;



    @Override
    public Users findUserById(Long userId) throws UserException {

        Users users = userRepository.findById(userId).orElseThrow(()->new UserException("User not found with ID" + userId));

        return users;
    }

    @Override
    public Users findUserprofileByJwt(String email1) throws UserException {

        String email = email1;
        Users users = userRepository.findByEmail(email);
        System.out.println("findUserProfile result" + users);
        if(users == null){
            throw new UserException("User not found with email "+ email);
        }
        return users;
    }

    @Override
    public Users updateUser(Long userId, Users req) throws UserException {
        Users users = findUserById(userId);
        if(req.getFullName()!=null){
            users.setFullName(req.getFullName());
        }
        if(req.getImage()!=null){
            users.setImage(req.getImage());
        }
        if(req.getBackgroundImage()!=null){
            users.setBackgroundImage(req.getBackgroundImage());
        }
        if(req.getBirthDate()!=null){
            users.setBirthDate(req.getBirthDate());
        }
        if(req.getLocation()!=null){
            users.setLocation(req.getLocation());
        }
        if(req.getBio()!=null){
            users.setBio(req.getBio());
        }
        if(req.getWebsite()!=null){
            users.setWebsite(req.getWebsite());
        }
        return userRepository.save(users);
    }

    @Override
    public Users followUser(Long userId, Users users) throws UserException {

        Users followToUser = findUserById(userId);

        if(users.getFollowings().contains(followToUser)&&followToUser.getFollowers().contains(users)){
            users.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(users);
        }else{
            users.getFollowings().add(followToUser);
            followToUser.getFollowers().add(users);

        }
        userRepository.save(followToUser);
        userRepository.save(users);

        return followToUser;
    }

    @Override
    public List<Users> searchUser(String query) {

        return userRepository.searchUser(query);
    }
}
