package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import controller.RegisterRequestValidator;

// Spring MVC 설정 
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer
{
	// @Controller로 등록되지 않은 경로를 처리 
	@Override 
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) 
	{
		configurer.enable();
	}

	@Override 
	public void configureViewResolvers(ViewResolverRegistry registry) 
	{ 
		// JSP 를 이용해 컨트롤러의 실행 결과를 보여주도록 함 
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
	
	// WebMvcConfigurer 인터페이스에 정의됨 
	// 요청 경로 - 뷰 이름 연결 
	@Override 
	public void addViewControllers(ViewControllerRegistry registry) 
	{
		registry.addViewController("/main").setViewName("main");
	}
	
	@Bean 
	public MessageSource messageSource() 
	{
		ResourceBundleMessageSource ms =
				new ResourceBundleMessageSource();
		ms.setBasename("message.label"); // src/main/resources/message/label.properties
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	// 스프링은 getValidator()가 리턴한 객체를 글로벌 범위 Validator로 사용 
//	@Override 
//	public Validator getValidator() 
//	{
//		return new RegisterRequestValidator();
//	}
}
