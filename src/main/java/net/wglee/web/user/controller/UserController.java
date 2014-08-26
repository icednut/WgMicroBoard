package net.wglee.web.user.controller;

import net.wglee.user.model.User;
import net.wglee.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @Author wangeun.lee@sk.com
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm() {
		return "user/login";
	}

	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess() {
		return "user/loginSuccess";
	}

	@RequestMapping(value = "/loginFail")
	public String loginFail() {
		return "user/loginFail";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String showRegistForm() {
		return "user/regist";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public RedirectView regist(User user) {
		userService.addUser(user);
		RedirectView view = new RedirectView("/user/login");
		view.setExposeModelAttributes(false);
		return view;
	}

	@RequestMapping(value = "/test")
	public String test() {
		return "/user/test";
	}
}
