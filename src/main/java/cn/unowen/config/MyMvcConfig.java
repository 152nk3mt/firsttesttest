package cn.unowen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import cn.unowen.interceptor.LoginInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("register");
				registry.addViewController("/change-password").setViewName("change-password");
				registry.addViewController("/login-log-detail").setViewName("login-log-detail");
				registry.addViewController("/re-book-detail").setViewName("re-book-detail");
				registry.addViewController("/re-log-detail").setViewName("re-log-detail");
				registry.addViewController("/login").setViewName("login");
			}

			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/v1/**", "/WEB-INF/resources/")
						.excludePathPatterns("/login", "/api/v1/user/login", "/api/v1/user/hello", "/api/v1/register");
			}
		};
		return adapter;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

}
