package net.wglee.web;

import net.wglee.config.DefaultWebAppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author wangeun.lee@sk.com
 */
public class DefaultServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{DefaultWebAppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}

	@Override
	protected String getServletName() {
		return super.getServletName();
	}
}
