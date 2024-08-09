package com.example.demo.service;

import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Twit;
import com.example.demo.model.Users;
import com.example.demo.repository.twitRepository;
import com.example.demo.request.TwitReplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TwitServiceImplementation implements  TwitService{

    @Autowired
    private twitRepository twitRepo;

    @Override
    public Twit createTwit(Twit req, Users users) throws UserException {

        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUsers(users);
        twit.setReply(false);
        twit.setTwit(true);
        twit.setVideo(req.getVideo());
        return twitRepo.save(twit);
    }

    @Override
    public List<Twit> findAllTwit() {

        return twitRepo.findAllByIsTwitTrueOrderByCreatedAtDesc();
    }

    @Override
    public Twit retwit(Long twitId, Users users) throws UserException, TwitException {
        Twit twit = findById(twitId);
        if(twit.getRetwitUsers().contains(users)){
            twit.getRetwitUsers().remove(users);
        }
        else{
            twit.getRetwitUsers().add(users);
        }
        return twitRepo.save(twit);
    }

    @Override
    public Twit findById(long twitId) throws TwitException {
        Twit twit = twitRepo.findById(twitId)
                .orElseThrow(()->new TwitException("Twit not found with id" + twitId));
        return twit;
    }

    @Override
    public void deleteTwitById(Long twitId, Long userId) throws TwitException, UserException {
        Twit twit = findById(twitId);
        if(!userId.equals(twit.getUsers().getId())){
            throw new UserException("You can't delete another user tweet");
        }
        twitRepo.deleteById(twit.getId());
    }

    @Override
    public Twit removeFromRetwit(Long twitId, Users users) throws TwitException, UserException {
        return null;
    }

    @Override
    public Twit createdReply(TwitReplyRequest req, Users users) throws TwitException {

       Twit replyFor = findById(req.getTwitId());
        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUsers(users);
        twit.setReply(true);
        twit.setTwit(false);
        twit.setReplyFor(replyFor);

       Twit savedReply = twitRepo.save(twit);
       twit.getReplyTwits().add(savedReply);
       twitRepo.save(replyFor);

       return replyFor;
    }

    @Override
    public List<Twit> getUserTwit(Users users) {
        return twitRepo.findByRetwitUsersContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(users, users.getId());
    }

    @Override
    public List<Twit> findLikesContainsUser(Users users) {
        return twitRepo.findByLikesUser_id(users.getId());
    }
}
