package com.pradeep.test.unit;

import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.utils.UserUtils;
import com.pradeep.web.controllers.ForgotMyPasswordController;
import com.pradeep.web.domain.frontend.BasicAccountPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;

    private PodamFactory podamFactory;

    @Before
    public void init(){
        mockHttpServletRequest= new MockHttpServletRequest();
        podamFactory=new PodamFactoryImpl();
    }

    @Test
    public void testPasswordResetEmailUrlConstruction() throws Exception{
        mockHttpServletRequest.setServerPort(8080); // default is 80

        String token=UUID.randomUUID().toString();
        long userId=123456;

        String expectedUrl="http://localhost:8080"+
                ForgotMyPasswordController.CHANGE_PASSWORD_PATH+ "?id=" + userId+ "&token=" +token;

        String actualurl=UserUtils.createPasswordResetUrl(mockHttpServletRequest,userId,token);

        Assert.assertEquals(expectedUrl,actualurl);
    }

    @Test
    public void mapWebUserToDomainUser(){
        BasicAccountPayload webUser=podamFactory.manufacturePojoWithFullData(BasicAccountPayload.class);
        webUser.setEmail("me@pradeep.com");

        User user =UserUtils.fromWebUserToDomainUser(webUser);
        Assert.assertNotNull(user);

        Assert.assertEquals(webUser.getUsername(),user.getUsername());
        Assert.assertEquals(webUser.getPassword(),user.getPassword());
        Assert.assertEquals(webUser.getFirstName(),user.getFirstName());
        Assert.assertEquals(webUser.getLastName(),user.getLastName());
        Assert.assertEquals(webUser.getEmail(),user.getEmail());
        Assert.assertEquals(webUser.getPhoneNumber(),user.getPhoneNumber());
        Assert.assertEquals(webUser.getCountry(),user.getCountry());
        Assert.assertEquals(webUser.getDescription(),user.getDescription());

    }

}
