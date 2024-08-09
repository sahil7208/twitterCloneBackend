package com.example.demo.controller;

import com.example.demo.dto.TwitDto;
import com.example.demo.dto.mapper.TwitDtoMapper;
import com.example.demo.exception.TwitException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Twit;
import com.example.demo.model.Users;
import com.example.demo.reponse.ApiResponse;
import com.example.demo.request.TwitReplyRequest;
import com.example.demo.service.TwitService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/twits")
public class TwitController {
    @Autowired
    private TwitService twitService;

    @Autowired
    private UserService userService;

    //private final Users use = new Users();
//    @Autowired
//    private Users users;

    @PostMapping("/create")
    public ResponseEntity<TwitDto> createTwit(@RequestBody Twit req
                                          //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        Twit twit = twitService.createTwit(req, users);

        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit,users);

        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);

    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyRequest req
                                              //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        Twit twit = twitService.createdReply(req, users);

        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit,users);

        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);

    }

    @PutMapping("/{twitId}/retwit")
    public ResponseEntity<TwitDto> reTwit(@PathVariable Long twitId
                                             //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        Twit twit = twitService.retwit(twitId, users);

        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit,users);

        return new ResponseEntity<>(twitDto, HttpStatus.OK);

    }

    @GetMapping("/{twitId}")
    public ResponseEntity<TwitDto> findTwitById(@PathVariable Long twitId
                                          //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        Twit twit = twitService.findById(twitId);

        TwitDto twitDto = TwitDtoMapper.toTwitDto(twit,users);

        return new ResponseEntity<>(twitDto, HttpStatus.OK);

    }

    @DeleteMapping("/{twitId}")
    public ResponseEntity<ApiResponse> deleteTwit(@PathVariable Long twitId
                                                  //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        twitService.deleteTwitById(twitId, users.getId());

       ApiResponse res = new ApiResponse();
       res.setMessage("Twit Deleted successfully");
       res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<TwitDto>> getAllTwits(
                                                //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

       List<Twit> twits = twitService.findAllTwit();

      List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits,users);

        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TwitDto>> getUsersAllTwits( @PathVariable Long userId
            //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        List<Twit> twits = twitService.getUserTwit(users);

        List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits,users);

        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TwitDto>> findTwitByLikesContainsUser( @PathVariable Long userId
                                                           //    ,@RequestHeader("Authorization")String jwt
    ) throws UserException, TwitException{

        Users users = userService.findUserprofileByJwt(new Users().getEmail());

        List<Twit> twits = twitService.findLikesContainsUser(users);

        List<TwitDto> twitDtos = TwitDtoMapper.toTwitDtos(twits,users);

        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }


}
