package net.wglee.user.service;

import net.wglee.user.model.SecurityUser;
import net.wglee.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author wangeun.lee@sk.com
 */
public class CommonUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new SecurityUser(user);
	}
}
