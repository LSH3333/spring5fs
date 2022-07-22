package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;


@Controller
public class RegisterController 
{
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) 
	{
		this.memberRegisterService = memberRegisterService;
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() 
	{
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue="false") Boolean agree, Model model)
	{
		 if(!agree) return "register/step1";
		 // step2 페이지 이동 전에 커맨드 객체 생성 
		 model.addAttribute("registerRequest", new RegisterRequest());
		// 약관 동의했다면 입력 폼 보여주도록 register/step2를 뷰 이름으로 리턴 
		return "register/step2";
	}
	
	// 약관 동의 안거치고 바로 회원가입 창 주소 입력시, 약관 동의 페이지로 이동하도록 리다이렉트 
	@GetMapping("/register/step2")
	public String handleStep2Get() 
	{
		return "redirect:/register/step1";
	}

	// 회원가입 form 데이터 받아서 처리하고 step3 (회원가입 완료 페이지) 이동 
	@PostMapping("/register/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors)
	{
		// 에러 있다면 step2 로 되돌아감 
		// @Valid에서 처리 
//		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) return "register/step2";
		
		try 
		{
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch(DuplicateMemberException ex) 
		{
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
			
	}
	
	// @Valid 가 붙은 커맨드 객체 검사할 Validator 설정 
	// 컨트롤러의 요청 처리 메서드 실행 전 매번 실행 
	@InitBinder
	protected void initBinder(WebDataBinder binder) 
	{
		binder.setValidator(new RegisterRequestValidator());
	} 
	

}
