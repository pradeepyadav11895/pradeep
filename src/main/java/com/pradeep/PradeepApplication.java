package com.pradeep;

import com.pradeep.backend.persistence.domain.backend.Role;
import com.pradeep.backend.persistence.domain.backend.User;
import com.pradeep.backend.persistence.domain.backend.UserRole;
import com.pradeep.backend.service.UserService;
import com.pradeep.enums.PlansEnum;
import com.pradeep.enums.RolesEnum;
import com.pradeep.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PradeepApplication implements CommandLineRunner {

	/** The application logger*/
	private static final Logger LOG=LoggerFactory.getLogger(PradeepApplication.class);

	@Autowired
	private UserService userService;

	@Value("${webmaster.username}")
	private String webmasterUsername;

	@Value("${webmaster.password}")
	private String webmasterPassword;

	@Value("${webmaster.email}")
	private String webmasterEmail;


	public static void main(String[] args) {
		SpringApplication.run(PradeepApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		User user=UserUtils.createBasicUSer(webmasterUsername,webmasterEmail);
		user.setPassword(webmasterPassword);
		Set<UserRole> userRoles=new HashSet<>();
		userRoles.add(new UserRole(user,new Role(RolesEnum.ADMIN)));
		LOG.debug("creating user with userName {}",user.getUsername());
		userService.createUser(user,PlansEnum.PRO,userRoles);
		LOG.info("User {} created",user.getUsername());
	}
}
