package net.wglee.config;

import net.wglee.user.service.CommonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author wangeun.lee@sk.com
 */
@Configuration
public class UserSecurityAdaptor extends WebSecurityConfigurerAdapter {
	@Autowired
	private CommonUserDetailsService commonUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
				userDetailsService(commonUserDetailsService).
				and().
				inMemoryAuthentication().withUser("testUser@naver.com").password("123456").roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				csrf().disable().
				formLogin().
				loginPage("/user/login").
				defaultSuccessUrl("/user/loginSuccess").
				failureUrl("/user/loginFail").
				usernameParameter("username").
				passwordParameter("password").
				permitAll().
				and().
				authorizeRequests().
				antMatchers("/user/regist").permitAll().
				anyRequest().authenticated();
	}

}
