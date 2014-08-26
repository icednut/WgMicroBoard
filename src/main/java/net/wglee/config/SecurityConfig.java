package net.wglee.config;

import net.wglee.user.service.CommonUserDetailsService;
import net.wglee.user.service.UserService;
import net.wglee.user.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @Author wangeun.lee@sk.com
 */
@Configuration
@EnableWebSecurity
@Import(value = {UserSecurityAdaptor.class})
public class SecurityConfig {
	@Bean
	public CommonUserDetailsService CommonUserDetailsService() {
		return new CommonUserDetailsService();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
