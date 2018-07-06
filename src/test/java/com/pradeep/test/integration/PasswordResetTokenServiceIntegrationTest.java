package com.pradeep.test.integration;

import com.pradeep.PradeepApplication;
import com.pradeep.backend.persistence.domain.backend.PasswordResetToken;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.service.PasswordResetTokenService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PradeepApplication.class)
public class PasswordResetTokenServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Rule public TestName testName= new TestName();

    @Test
    public void testCreateNewTokenForUserEmail() throws Exception{
        User user=createUser(testName);

        PasswordResetToken passwordResetToken=passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        Assert.assertNotNull(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getToken());

    }
    @Test
    public void testFindByToken() throws Exception{

    }


}
