package com.pradeep.utils;

import com.pradeep.backend.persistence.domain.backend.User;

public class UserUtils {

    private UserUtils(){
        throw new AssertionError("Non instantiable");
    }

    public  static User createBasicUSer(){
        User user=new User();
        user.setUsername("basicUser");
        user.setPassword("secret"); // secret is password
        user.setEmail("abc@gmail.com");
        user.setFirstName("Pradeep");
        user.setLastName("yadav");
        user.setPhoneNumber("85285212369");
        user.setCountry("india");
        user.setEnabled(true);
        user.setDescription("pradeep application");
        user.setProfileImageUrl("www.gmail.com");
        return user;
    }


}
