package com.example.demo.controller;

import com.example.demo.dto.LikeDto;
import com.example.demo.dto.mapper.LikeDtoMapper;
import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.Users;
import com.example.demo.service.LikeService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class LikeController {
    @Autowired
    public UserService userService;
    @Autowired
    private LikeService likeService;

    @PostMapping("/{twitId}/likes")
    public ResponseEntity<LikeDto> likeTwit(@PathVariable Long twitId) throws UserException, TwitException {

        Users users = userService.findUserprofileByJwt(new Users().getEmail());
        Like like = likeService.likeTwit(twitId, users);

        LikeDto likeDto = LikeDtoMapper.toLikeDto(like, users);



        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
    }

    @PostMapping("/twit/{twitId}")
    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long twitId) throws UserException, TwitException {

        Users users = userService.findUserprofileByJwt(new Users().getEmail());
        List<Like> likes = likeService.getAllLikes(twitId);

        List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(likes, users);



        return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);
    }

}
