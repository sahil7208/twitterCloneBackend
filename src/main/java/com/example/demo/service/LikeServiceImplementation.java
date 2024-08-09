package com.example.demo.service;

import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.Twit;
import com.example.demo.model.Users;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.twitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TwitService twitService;

    @Autowired
    private twitRepository twitRepository;


    @Override
    public Like likeTwit(Long twitId, Users users) throws UserException, TwitException {
        Like isLikeExist = likeRepository.isLikeExist(users.getId(), twitId);
        if(isLikeExist != null){
            likeRepository.deleteById(isLikeExist.getId());
            return isLikeExist;
        }

        Twit twit = twitService.findById(twitId);

        Like like = new Like();
        like.setTwit(twit);
        like.setUser(users);

        Like savedLike = likeRepository.save(like);

        twit.getLikes().add(savedLike);
        twitRepository.save(twit);

        return savedLike;
    }

    @Override
    public List<Like> getAllLikes(Long twitId) throws TwitException {

        Twit twit = twitService.findById(twitId);

        List<Like> likes = likeRepository.findByTwitId(twitId);

        return likes;
    }
}
