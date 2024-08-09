package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String fullName;
    private String password;
    private String birthDate;
    private String location;
    private String website;
    private String mobile;
    private String image;
    private String bio;
    private String backgroundImage;
    private boolean req_user;
    private boolean login_with_google;


@JsonIgnore
@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
    private List<Twit>twit = new ArrayList<>();

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @Embedded
    private Verification verification;

@JsonIgnore
    @ManyToMany
    private List<Users>followers = new ArrayList<>();

@JsonIgnore
    @ManyToMany
    private List<Users>followings = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public boolean isLoginWithGoogle() {
        return login_with_google;
    }

    public void setLoginWithGoogle(boolean login_with_google) {
        this.login_with_google = login_with_google;
    }
}
