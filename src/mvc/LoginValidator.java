package mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
	MemberDao dao;
	
	public LoginValidator(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		MemberVo vo = (MemberVo) obj;
		boolean b = true; 	// true: 에러없음, false: 에러있음
		
		if(vo.getUserid() == null || vo.getUserid().trim().isEmpty()) {
			err.rejectValue("userid", "아이디를 입력하세요");
			b=false;
		}
		if(vo.getUserpwd() == null || vo.getUserpwd().trim().isEmpty()) {
			err.rejectValue("userpwd", "비밀번호를 입력하세요");
			b=false;
		}
		
		//가입된 유저인지를 판단
		if(b) {
			if(!dao.login(vo)) {
				err.rejectValue("msg", "당신은 가입된 유저가 아닐껄??");
			}
		}
		
		
		
	}

}
