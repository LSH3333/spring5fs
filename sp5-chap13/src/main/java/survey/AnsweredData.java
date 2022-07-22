package survey;

import java.util.List;

// 설문 항목에 대한 답변, 응답자 정보 
public class AnsweredData 
{
	private List<String> responses; // 답변 목록 
	private Respondent res; // 응답자 정보 
	
	public List<String> getResponses() { return responses; }
	
	public void setResponses(List<String> responses) { this.responses = responses; } 
		
	public Respondent getRes() { return res; } 
		
	public void setRes(Respondent res) { this.res = res; } 	
}
