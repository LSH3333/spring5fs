package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.RegisterController;
import spring.MemberRegisterService;

@Configuration 
public class ControllerConfig 
{
	// MemberConfig.java에서 빈 객체로 추가됨   
	@Autowired
	private MemberRegisterService memberRegSvc;
	
	@Bean 
	public RegisterController registerController() 
	{		
		RegisterController controller = new RegisterController();
		controller.setMemberRegisterService(memberRegSvc);
		return controller;		
	}

}
