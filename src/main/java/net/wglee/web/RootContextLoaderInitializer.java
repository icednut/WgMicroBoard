package net.wglee.web;

import net.wglee.config.ApplicationConfig;
import org.springframework.core.Conventions;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;

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

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		registerServletFilter(servletContext, characterEncodingFilter);

////		servletContext.setInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM, EnvironmentInitializer.class.getName());
//		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
//		registerServletFilter(servletContext, hiddenHttpMethodFilter);
//
//		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
//		registerServletFilter(servletContext, httpPutFormContentFilter);
//
//		OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
//		registerServletFilter(servletContext, openEntityManagerInViewFilter);
//
//		CacheExpiresFilter cacheExpiresFilter = new CacheExpiresFilter();
//		String filterName = Conventions.getVariableName(cacheExpiresFilter);
//		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, cacheExpiresFilter);
//		registration.setAsyncSupported(false);
//		registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
//
//		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
//		registerServletFilter(servletContext, springSecurityFilterChain);

		registerServlets(servletContext, rootContext);
	}

	private void registerServlets(ServletContext servletContext, AnnotationConfigWebApplicationContext rootContext) {
		ServletRegistration.Dynamic springDispatcher = servletContext.addServlet("SpringDispatcherServlet", new DispatcherServlet(rootContext));
		springDispatcher.setLoadOnStartup(1);
		springDispatcher.addMapping("/");
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
