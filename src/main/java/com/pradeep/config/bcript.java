package com.pradeep.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class bcript {

    public static  void main(String args[]){
        System.out.println("enc-- "+new BCryptPasswordEncoder().encode("secret"));
    }
}
