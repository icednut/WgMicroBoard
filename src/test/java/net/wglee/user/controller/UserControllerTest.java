package net.wglee.user.controller;

import net.wglee.config.ApplicationConfig;
import net.wglee.config.WebMvcConfig;
import net.wglee.user.model.User;
import net.wglee.user.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Author wangeun.lee@sk.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class UserControllerTest {
	@Autowired
	@InjectMocks
	private UserController controller;

	@Mock
	private UserService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShowLoginForm() throws Exception {
		String viewName = controller.showLoginForm();

		assertEquals("user/login", viewName);
	}

	@Test
	public void testShowLoginSuccess() throws Exception {
		String viewName = controller.loginSuccess();

		assertEquals("user/loginSuccess", viewName);
	}

	@Test
	public void testShowRegistForm() throws Exception {
		String viewName = controller.showRegistForm();

		assertEquals("user/regist", viewName);
	}

	@Test
	public void testRegist() throws Exception {
		User user = new User();
		user.setEmail("test@test.com");
		user.setUsername("testNickName");
		user.setPassword("12345678!");

		RedirectView view = controller.regist(user);

		assertNotNull(view);
	}
}
