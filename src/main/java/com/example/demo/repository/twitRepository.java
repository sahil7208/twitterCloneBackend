package com.example.demo.repository;

import com.example.demo.model.Twit;
import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface twitRepository extends JpaRepository<Twit, Long> {

    List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();
    @Query("SELECT t FROM Twit t WHERE :users MEMBER OF t.retwitUsers OR t.users.id = :userId AND t.isTwit = true ORDER BY t.createdAt DESC")
    List<Twit> findByRetwitUsersContainsOrUser_IdAndIsTwitTrueOrderByCreatedAtDesc(Users users,Long userId);
    List<Twit> findByLikesContainingOrderByCreatedAtDesc(Users users);
    @Query("SELECT t FROM Twit t JOIN t.likes l WHERE l.user.id=:userId")
    List<Twit> findByLikesUser_id(Long userId);
}
