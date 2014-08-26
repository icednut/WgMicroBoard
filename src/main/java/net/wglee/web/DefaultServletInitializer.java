package net.wglee.web;

import net.wglee.config.ApplicationConfig;
import net.wglee.config.DefaultWebAppConfig;
import net.wglee.config.SecurityConfig;
import org.springframework.core.Conventions;
import org.springframework.core.annotation.Order;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.*;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangeun.lee@sk.com
 */
@Order(1)
public class DefaultServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{
				ApplicationConfig.class,
				SecurityConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{
				DefaultWebAppConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/*"};
	}

	@Override
	protected String getServletName() {
		return super.getServletName();
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		CacheExpiresFilter cacheExpiresFilter = new CacheExpiresFilter();
//		String filterName = Conventions.getVariableName(cacheExpiresFilter);

		return new Filter[]{
//				new DelegatingFilterProxy("springSecurityFilterChain"),
				new HiddenHttpMethodFilter(),
				new HttpPutFormContentFilter(),
				new OpenEntityManagerInViewFilter(),
				characterEncodingFilter,
				cacheExpiresFilter
		};
	}

	@Override
	protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
		return super.registerServletFilter(servletContext, filter);
	}

	class CacheExpiresFilter extends OncePerRequestFilter {
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.
			filterChain.doFilter(request, response);
		}
	}
}
