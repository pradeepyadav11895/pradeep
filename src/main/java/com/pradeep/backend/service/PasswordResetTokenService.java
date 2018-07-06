package com.pradeep.backend.service;

import com.pradeep.backend.persistence.domain.backend.PasswordResetToken;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.repositories.PasswordResetTokenRepository;
import com.pradeep.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationTimeInMinutes;

    /** The application logger*/
    private static final Logger LOG=LoggerFactory.getLogger(PasswordResetTokenService.class);



    @Transactional
    public PasswordResetToken createPasswordResetTokenForEmail(String email){
        PasswordResetToken passwordResetToken=null;

        User user=userRepository.findByEmail(email);

        if(null !=user){
            String token=UUID.randomUUID().toString();
            LocalDateTime now=LocalDateTime.now(Clock.systemUTC());
            passwordResetToken=new PasswordResetToken(token,user,now,tokenExpirationTimeInMinutes);

            passwordResetToken=passwordResetTokenRepository.save(passwordResetToken);
            LOG.debug("Successfully created token {} "+token,user.getUsername());
        }else {
            LOG.warn("we couldnot find a user for given email{} ",email);
        }
        return passwordResetToken;
    }

    public PasswordResetToken findByToken(String token){
        return passwordResetTokenRepository.findByToken(token);
    }

}
