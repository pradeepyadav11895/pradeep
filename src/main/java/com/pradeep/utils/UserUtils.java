package com.pradeep.utils;

import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.web.controllers.ForgotMyPasswordController;
import com.pradeep.web.domain.frontend.BasicAccountPayload;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    private UserUtils(){
        throw new AssertionError("Non instantiable");
    }

    public  static User createBasicUSer(String username,String email){
        User user=new User();
        user.setUsername(username);
        user.setPassword("secret"); // secret is password
        user.setEmail(email);
        user.setFirstName("Pradeep");
        user.setLastName("yadav");
        user.setPhoneNumber("85285212369");
        user.setCountry("india");
        user.setEnabled(true);
        user.setDescription("pradeep application");
        user.setProfileImageUrl("www.gmail.com");
        return user;
    }


    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String passwordResultUrl=
                request.getScheme() +
                        "://" +
                        request.getServerName() +
                        ":" +
                        request.getServerPort() +
                        request.getContextPath() +
                        ForgotMyPasswordController.CHANGE_PASSWORD_PATH +
                        "?id=" +
                        userId +
                        "&token=" +
                        token;
        return  passwordResultUrl;

    }

    public static <T extends BasicAccountPayload  > User fromWebUserToDomainUser(T frontendPayload) {
        User user= new User();
        user.setUsername(frontendPayload.getUsername());
        user.setPassword(frontendPayload.getPassword());
        user.setFirstName(frontendPayload.getFirstName());
        user.setLastName(frontendPayload.getLastName());
        user.setEmail(frontendPayload.getEmail());
        user.setPhoneNumber(frontendPayload.getPhoneNumber());
        user.setCountry(frontendPayload.getCountry());
        user.setEnabled(true);
        user.setDescription(frontendPayload.getDescription());

        return user;

    }
}
