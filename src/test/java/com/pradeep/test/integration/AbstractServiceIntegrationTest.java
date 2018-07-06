package com.pradeep.test.integration;

import com.pradeep.backend.persistence.domain.backend.Role;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.service.UserService;
import com.pradeep.enums.PlansEnum;
import com.pradeep.enums.RolesEnum;
import com.pradeep.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public  abstract class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String username=testName.getMethodName();
        String email=testName.getMethodName()+"@pradeep.com";

        Set<UserRole> userRoles=new HashSet<>();
        User basicUser=UserUtils.createBasicUSer(username,email);
        userRoles.add(new UserRole(basicUser,new Role(RolesEnum.BASIC)));
        return userService.createUser(basicUser,PlansEnum.BASIC,userRoles);
    }
}
