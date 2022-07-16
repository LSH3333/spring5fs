package survey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey") // 이 클래스는 "/survey" 경로 처리  
public class SurveyController 
{
	// GET 방식의 "/survey" 경로 요청 처리 (주소창 직접 입력은 GET 방식) 
	@GetMapping
	public String form() 
	{
		return "survey/surveyForm";			
	}

	// POST 방식의 "/survey" 경로 요청 처리  
	// surveyForm.jsp에서 form이 submit되서 오면 여기서 처리됨 
	@PostMapping 
	public String submit(@ModelAttribute("ansData") AnsweredData data) // 커맨드 객체 
	{
		return "survey/submitted";
	}
}
