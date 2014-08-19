package net.wglee.web;

import net.wglee.config.ApplicationConfig;
import org.springframework.core.Conventions;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

/**
 * @Author wangeun.lee@sk.com
 */
public class RootContextLoaderInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		registerServletFilter(servletContext, characterEncodingFilter);

		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		registerServletFilter(servletContext, hiddenHttpMethodFilter);

		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
		registerServletFilter(servletContext, httpPutFormContentFilter);

		OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		registerServletFilter(servletContext, openEntityManagerInViewFilter);

		CacheExpiresFilter cacheExpiresFilter = new CacheExpiresFilter();
		String filterName = Conventions.getVariableName(cacheExpiresFilter);
		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, cacheExpiresFilter);
		registration.setAsyncSupported(false);
		registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
	}

	protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
		String filterName = Conventions.getVariableName(filter);
		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
		registration.setAsyncSupported(false);
		registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
		return registration;
	}

	private EnumSet<DispatcherType> getDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
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
