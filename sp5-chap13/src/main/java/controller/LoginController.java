package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

// 로그인 요청 처리하는 컨트롤러 클래스 
@Controller
@RequestMapping("/login")
public class LoginController 
{
	private AuthService authService;
	
	public void setAuthService(AuthService authService) 
	{
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand) 
	{
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors) 
	{
		// 에러 있으면 errors에 담김 
		new LoginCommandValidator().validate(loginCommand, errors);
		
		if(errors.hasErrors())
		{
			return "login/loginForm";
		}
		
		// login 시도함 
		try 
		{
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(), loginCommand.getPassword());
			
			// TODO 세션에 authInfo 저장해야 함 
			return "login/loginSuccess";
		} catch(WrongIdPasswordException e) 
		{
			errors.reject("idPasswordNotMatching"); // global error 
			return "login/loginForm";
		}
	}
}
