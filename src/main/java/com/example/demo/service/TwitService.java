package com.example.demo.service;

import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Twit;
import com.example.demo.model.Users;
import com.example.demo.request.TwitReplyRequest;

import java.util.List;

public interface TwitService {

    public Twit createTwit(Twit req, Users users) throws UserException;
public List<Twit> findAllTwit();
public Twit retwit(Long twitId, Users users) throws UserException, TwitException;
public Twit findById(long twitId) throws TwitException;

public void deleteTwitById(Long twitId, Long userId) throws TwitException, UserException;

public Twit removeFromRetwit(Long twitId, Users users) throws TwitException, UserException;

public Twit createdReply(TwitReplyRequest req, Users users) throws  TwitException;

public List<Twit> getUserTwit(Users users);

public List<Twit> findLikesContainsUser(Users users);


}
