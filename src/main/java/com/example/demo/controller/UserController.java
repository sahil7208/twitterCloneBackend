package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.mapper.UserDtoMapper;
import com.example.demo.exception.UserException;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto>getUserProfile(
            @RequestHeader("Authorization") String jwt
            ) throws UserException{

        Users users = userService.findUserprofileByJwt(jwt);
        System.out.println("userController result" + users);
        UserDto userDto = UserDtoMapper.toUserDto(users);
        System.out.println("userDto result" + userDto);
       // userDto.setReq_user(true);


        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDto>getUserById(@PathVariable Long userId
            //    ,@RequestHeader("Authorization") String jwt
    ) throws UserException{

        Users reqUser = userService.findUserprofileByJwt(new Users().getEmail());
        Users users = userService.findUserById(userId);
        UserDto userDto = UserDtoMapper.toUserDto(users);
        userDto.setReq_user(UserUtil.isReqUser(reqUser, users));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, users));


        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>>searchUser(@RequestParam String query
                                                    //    ,@RequestHeader("Authorization") String jwt
    ) throws UserException{

        Users reqUser = userService.findUserprofileByJwt(new Users().getEmail());
        List<Users> users = userService.searchUser(query);
        List<UserDto> userDtos = UserDtoMapper.toUserDtos(users);
      //  userDto.setReq_user(UserUtil.isReqUser(reqUser, users));
      //  userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, users));


        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto>searchUser(@RequestBody Users req
                                                   //    ,@RequestHeader("Authorization") String jwt
    ) throws UserException{

        Users reqUser = userService.findUserprofileByJwt(new Users().getEmail());
        Users users = userService.updateUser(reqUser.getId(),req);
        UserDto userDto = UserDtoMapper.toUserDto(users);



        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<UserDto>searchUser(@PathVariable Long userId
                                             //    ,@RequestHeader("Authorization") String jwt
    ) throws UserException{

        Users reqUser = userService.findUserprofileByJwt(new Users().getEmail());
        Users users = userService.followUser(userId,reqUser);
        UserDto userDto = UserDtoMapper.toUserDto(users);
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, users));



        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }

}
