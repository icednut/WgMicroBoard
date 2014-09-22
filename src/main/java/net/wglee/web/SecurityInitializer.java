package net.wglee.web;

import net.wglee.config.SecurityConfig;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @Author wangeun.lee@sk.com
 */
@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
