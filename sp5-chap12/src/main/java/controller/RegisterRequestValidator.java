package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;


import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator 
{
	private static final String emailRegExp = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
					"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	public RegisterRequestValidator() 
	{
		pattern = Pattern.compile(emailRegExp);
	}

	// Validator의 supports 메서드는 주어지는 clazz가 커맨드 객체와 호환가능한지 검사한다 
	@Override
	public boolean supports(Class<?> clazz) 
	{	
		// RegisterRequest가 clazz 와 같거나 superclass 인지 확인 
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	// validate 메서드는 에러가 있는지 검사한다 
	// target: 검사 대상 객체 
	@Override	
	public void validate(Object target, Errors errors) 
	{
		RegisterRequest regReq = (RegisterRequest)target;
		
		// 검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사 
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty())
		{
			// 올바르지 않다면 Errors의 rejectValue() 메서드를 이용해서 에러 코드 저장
			// "email" 프로퍼티의 에러 코드 "required" 추가 
			errors.rejectValue("email", "required");
		}
		else 
		{
			// 정규 표현식을 이용해 이메일이 올바른지 확인 
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) 
			{ errors.rejectValue("email", "bad"); }
		}
		
		// 검사 대상 객체 (target)의 "name" 프로퍼티가 null 이거나 공백문자로만 되어 있는 경우 
		// "name" 프로퍼티의 에러 코드로 "required"를 추가한다 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");

		if(!regReq.getPassword().isEmpty()) 
		{
			if(!regReq.isPasswordEqualToConfirmPassword()) 
			{
				errors.rejectValue("comfirmPassword", "nomatch");
			}
		}
	}
	
}


