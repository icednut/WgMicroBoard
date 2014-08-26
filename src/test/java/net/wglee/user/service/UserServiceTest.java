package net.wglee.user.service;

import net.wglee.config.ApplicationConfig;
import net.wglee.config.DefaultWebAppConfig;
import net.wglee.user.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

/**
 * @Author wangeun.lee@sk.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, DefaultWebAppConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void testAdddUser() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		user.setUsername("testUser");
		user.setPassword("12345678!");
		user.setRoles("NORMAL_USER");

		userService.addUser(user);
	}
}
