package net.wglee.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author wangeun.lee@sk.com
 */
@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
