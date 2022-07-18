package survey;

import java.util.Collections;
import java.util.List;

public class Question 
{
	private String title;
	private List<String> options;
	
	public Question(String title, List<String> options) 
	{
		this.title = title;
		this.options = options;
	}
	
	public Question(String title) 
	{
		this(title, Collections.<String>emptyList());
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public List<String> getOptions() 
	{
		return options;
	}
	
	// true시 객관식  
	public boolean isChoice() 
	{
		return options != null && !options.isEmpty();
	}
}