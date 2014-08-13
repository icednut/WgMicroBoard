package net.wglee.user.service;

import net.wglee.config.ApplicationConfig;
import net.wglee.user.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author wangeun.lee@sk.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		user.setNickname("testUser");
		user.setPassword("12345678!");

		userService.addUser(user);
	}
}
