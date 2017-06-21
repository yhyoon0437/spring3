package mvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator{
	/*private static final String emailRegExp =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;나중에 써먹자*/

	public MemberValidator() {
		//pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberVo vo = (MemberVo) target;
		
		if(vo.getUserid()==null || vo.getUserid().trim().isEmpty()){
			errors.rejectValue("userid", "아이디를 입력해주세요!!");
		}
		if(vo.getUserpwd()==null || vo.getUserpwd().trim().isEmpty()){
			errors.rejectValue("userpwd", "비밀번호를 입력해 주세요!!");
		}
		
		
		
		/*if (regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}*/
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userid", "required");
		ValidationUtils.rejectIfEmpty(errors, "userpwd", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmpwd", "required"); 몰라도 될듯*/
		/*if (!regReq.isPwdEqual()) {
				errors.rejectValue("confirmpwd", "nomatch");
		}*/
	}

}