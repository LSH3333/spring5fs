package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

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
	public String handleStep2(HttpServletRequest request)
	{
		// agree 파라미터의 value 
		String agreeParam = request.getParameter("agree");
		if(agreeParam == null || !agreeParam.equals("true"))
		{
			return "register/step1"; // 약관 동의 다시 보여줌 
		}
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
	public String handleStep3(RegisterRequest regReq)
	{
		try 
		{
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch(DuplicateMemberException ex) 
		{
			return "register/step2";
		}
	}
}
