package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

    public Users findByEmail(String email);

    @Query("SELECT DISTINCT u FROM Users u WHERE u.fullName LIKE %:query% OR u.email LIKE %:query%")
    public List<Users> searchUser(@Param("query")String query);

}
