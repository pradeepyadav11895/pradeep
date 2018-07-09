package com.pradeep;

import com.pradeep.backend.service.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PradeepApplicationTests {

	@Autowired
	private I18NService i18NService;

	@Test
	public void testMessageByLocaleService(){
		String expectedResult="Bootstrap starter template";
		String messageId="index.main.callout";
		String actual=i18NService.getMessage(messageId);
		Assert.assertEquals("The actual and Expected Strings not macth",expectedResult,actual);
	}

}
