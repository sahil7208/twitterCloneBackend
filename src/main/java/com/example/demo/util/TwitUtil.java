package com.example.demo.util;

import com.example.demo.model.Like;
import com.example.demo.model.Twit;
import com.example.demo.model.Users;

public class TwitUtil {

    public final static boolean isLikedByReqUser(Users reqUser, Twit twit){

        for (Like like : twit.getLikes()){
            if(like.getUser().getId().equals(reqUser.getId())){
                return true;
            }
        }
return false;
    }

    public final static boolean isRetwitedByReqUser(Users reqUser, Twit twit){
        for(Users users : twit.getRetwitUsers()){
            if (users.getId().equals(reqUser.getId())){
                return true;
            }
        }
        return false;
    }

}
