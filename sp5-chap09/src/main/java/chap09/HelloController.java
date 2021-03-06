package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 해당 클래스는 스프링 MVC에서 컨트롤러로 사용 
public class HelloController 
{
	//메서드가 처리할 요청 경로 지정 
	@GetMapping("/hello")
	public String hello(Model model, 
			@RequestParam(value = "name", required = false) String name)	
	{
		model.addAttribute("greeting", "안녕하세요, " + name);
		return "hello";
	}
}
