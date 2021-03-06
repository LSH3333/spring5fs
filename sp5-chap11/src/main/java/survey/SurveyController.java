package survey;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey") // 이 클래스는 "/survey" 경로 처리  
public class SurveyController 
{
	// GET 방식의 "/survey" 경로 요청 처리 (주소창 직접 입력은 GET 방식) 
//	@GetMapping
//	public String form(Model model) 
//	{
//		List<Question> questions = createQuestions();
//		model.addAttribute("questions", questions);		
//		return "survey/surveyForm";			
//	}
	
	@GetMapping
	public ModelAndView form()
	{
		List<Question> questions = createQuestions();
		ModelAndView mav = new ModelAndView();
		mav.addObject(questions); // 뷰에 전달할 모델 데이터 추가 
		mav.setViewName("survey/surveyForm"); // 뷰 이름 설정 
		return mav;
	}
	
	private List<Question> createQuestions() 
	{
		Question q1 = new Question("당신의 역할은 무엇입니까?", 
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", 
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	// POST 방식의 "/survey" 경로 요청 처리  
	// surveyForm.jsp에서 form이 submit되서 오면 여기서 처리됨 
	@PostMapping 
	public String submit(@ModelAttribute("ansData") AnsweredData data) // 커맨드 객체 
	{
		return "survey/submitted";
	}
}
