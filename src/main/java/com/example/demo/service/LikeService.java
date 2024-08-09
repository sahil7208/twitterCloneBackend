package com.example.demo.service;

import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.Users;

import java.util.List;

public interface LikeService {

    public Like likeTwit(Long twitId, Users users) throws UserException, TwitException;

    public List<Like> getAllLikes(Long twitId) throws TwitException;

}
