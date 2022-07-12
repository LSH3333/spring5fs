package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController 
{
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
			return "register/step1";
		}
		return "register/step2";
	}

}
