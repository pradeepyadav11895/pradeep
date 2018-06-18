package com.pradeep.test.integration;


import com.pradeep.PradeepApplication;
import com.pradeep.backend.persistence.domain.backend.Plan;
import com.pradeep.backend.persistence.domain.backend.Role;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.persistence.repositories.PlanRepository;
import com.pradeep.backend.persistence.repositories.RoleRepository;
import com.pradeep.backend.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Classes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PradeepApplication.class)
public class RepositoriesIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private static final int BASIC_PLAN_ID=1;
    private static final int BASIC_ROLE_ID=1;

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan()throws Exception{
        Plan basicPlan=createBasicPlan();
        planRepository.save(basicPlan);
        Optional<Plan> reterivedPlan=planRepository.findById(BASIC_PLAN_ID);
        Assert.assertNotNull(reterivedPlan);
        
    }

    @Test
    public void testCreateNewRole()throws Exception{
        Role userRole=createBasicRole();
        roleRepository.save(userRole);
        Optional<Role> reterivedRole=roleRepository.findById(BASIC_ROLE_ID);
        Assert.assertNotNull(reterivedRole);

    }
    @Test
    public void testCreateNewUser() throws Exception{
        Plan basicPlan=createBasicPlan();
        planRepository.save(basicPlan);

        User basicUser=createBasicUSer();
        basicUser.setPlan(basicPlan);

        Role basicRole=createBasicRole();
        Set<UserRole> userRoles=new HashSet<>();

        UserRole userRole=new UserRole();
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole ur :userRoles){
            roleRepository.save(ur.getRole());
        }

        basicUser=userRepository.save(basicUser);
        Optional<User> newlyCreatedUser=userRepository.findById(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.get().getId() !=0);
        Assert.assertNotNull(newlyCreatedUser.get().getPlan());
        Assert.assertNotNull(newlyCreatedUser.get().getPlan().getId());
        Set<UserRole> newlyCreateduserRoles=newlyCreatedUser.get().getUserRoles();
        for (UserRole ur:newlyCreateduserRoles){
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }


    private Plan createBasicPlan() {
        Plan plan=new Plan();
        plan.setId(BASIC_PLAN_ID);
        plan.setName("Basic");
        return plan;
    }


    private Role createBasicRole() {
        Role role= new Role();
        role.setId(BASIC_PLAN_ID);
        role.setName("ROLE_USER");
        return role;
    }


    private User createBasicUSer(){
        User user=new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
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
