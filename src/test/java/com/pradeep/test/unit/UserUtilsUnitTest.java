package com.pradeep.test.unit;

import com.pradeep.utils.UserUtils;
import com.pradeep.web.controllers.ForgotMyPasswordController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void init(){
        mockHttpServletRequest= new MockHttpServletRequest();
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

}
