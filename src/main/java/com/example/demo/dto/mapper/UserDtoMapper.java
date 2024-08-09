package com.example.demo.dto.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Users;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    public static UserDto toUserDto(Users  users){
        UserDto userDto = new UserDto();
        userDto.setId(users.getId());
        userDto.setEmail(users.getEmail());
        userDto.setFullName(users.getFullName());
        userDto.setBackgroundImage(users.getBackgroundImage());
        userDto.setBio(userDto.getBio());
        userDto.setBirthDate(users.getBirthDate());
        userDto.setFollowers(toUserDtos(users.getFollowers()));
        userDto.setFollowing(toUserDtos(users.getFollowings()));
        userDto.setLogin_with_google(users.isLogin_with_google());
        userDto.setLocation(users.getLocation());
       // userDto.setVerified(false);

        return userDto;
    }

    public static List<UserDto> toUserDtos(List<Users>followers){
        List<UserDto>userDtos = new ArrayList<>();

        for(Users users : followers){
            UserDto userDto = new UserDto();
            userDto.setId(users.getId());
            userDto.setEmail(users.getEmail());
            userDto.setFullName(users.getImage());
            userDtos.add(userDto);
        }

        return userDtos;
    }

}
