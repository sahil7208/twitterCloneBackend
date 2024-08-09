package com.example.demo.util;

import com.example.demo.model.Users;
import org.springframework.security.core.userdetails.User;

public class UserUtil {

    public static final boolean isReqUser(Users reqUser, Users user2){


        return reqUser.getId().equals(user2.getId());
    }

    public static final boolean isFollowedByReqUser(Users reqUser, Users user2){

        return reqUser.getFollowings().contains(user2);
    }

}
