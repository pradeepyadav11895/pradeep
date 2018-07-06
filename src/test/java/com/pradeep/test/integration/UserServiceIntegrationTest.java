package com.pradeep.test.integration;

import com.pradeep.PradeepApplication;
import com.pradeep.backend.persistence.domain.backend.Role;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.service.UserService;
import com.pradeep.enums.PlansEnum;
import com.pradeep.enums.RolesEnum;
import com.pradeep.utils.UserUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PradeepApplication.class)
public class UserServiceIntegrationTest  extends AbstractServiceIntegrationTest{

    @Rule public TestName testName= new TestName();

    @Test
    public void testCreateNewUser() throws Exception{


           User user = createUser(testName);
           Assert.assertNotNull(user);
           Assert.assertNotNull(user.getId());
           // cooemnt

    }

}
