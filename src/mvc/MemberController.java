package mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	MemberDao dao;

	public MemberController() {

	}

	public MemberController(MemberDao dao) {
		this.dao = dao;
	}
	//요청정보가 value값으로 들어왔을때 view를 실행
	//메소드의 형태가 get이나 post일때 실행
	@RequestMapping(value="member/view.do", method={RequestMethod.GET, RequestMethod.POST})
	public String view(MemberVo vo) {
		MemberVo v = dao.view(vo);
		vo.setUserpwd(v.getUserpwd());
		return "view"; // member 폴더 안의 view.jsp에 결과가 표시된다
	}
	
	//form의 action태그를 타고 input.do가 들어오므로 method타입은 POST만 @@@
	
	@RequestMapping(value="member/input.do", method={RequestMethod.POST,RequestMethod.GET})
	//input 매개변수에 들어오는 값은 input.jsp의 userid와 userpwd가 들어옴
	//  input.jsp의 name값은(userid,userpwd) MemberVo의 변수명과 같아야 함.
	public Object input(MemberVo vo, Errors errors){//반환 타입은 Object형으로
		
//		return mv; 가 원형이고 메소드 반환유형은 Object type..(ModelAndView로 해도되는데 걍 object로함)*/
		ModelAndView mv = new ModelAndView();
		new MemberValidator().validate(vo, errors);
		
		
		String msg = ""	;
		
		
		if(errors.getErrorCount()>0){
			List<ObjectError> list = errors.getAllErrors();
			for(ObjectError oe : list){
				FieldError fe = (FieldError) oe;
				mv.addObject(fe.getField(),fe.getCode());
				mv.setViewName("input");
				
			}
			
		}else{
			int r = dao.input(vo);
			if(r>0) msg = "회원가입을 축하합니다~^^.";
			else msg = "회원가입 중 오류 발생..다시시도해주세요.";
			mv.addObject("vo",vo);
			mv.addObject("msg",msg); //map구조 key값, value 값
			mv.setViewName("input_result"); //msg 값이 input_result로 값이 넘어감
			
		}
		
		return mv;
	}
	
	@RequestMapping(value="member/list.do",method={RequestMethod.POST,RequestMethod.GET})
	   public Object list(MemberVo vo){ //userid만 들어옴(setter 실행.)
	      ModelAndView mv = new ModelAndView();
	      List<MemberVo> list = new ArrayList<MemberVo>();
	      list = dao.list(vo);
	      mv.addObject("list",list);
	      mv.setViewName("list");//mv.setViewName("파일명") 보여줄 파일명....
	      
	      return mv; 
	   }
	@RequestMapping(value="member/inputView.do",method={RequestMethod.POST,RequestMethod.GET})
	   public String inputView(MemberVo vo,Errors errors){ //userid만 들어옴(setter 실행.)
		new MemberValidator().validate(vo, errors);
		
		return "input";
	}
	
	
	@RequestMapping(value="member/modify.do",method={RequestMethod.POST,RequestMethod.GET})
	   public Object modify(MemberVo vo){ //userid만 들어옴(setter 실행.)
		MemberVo v = dao.view(vo);
		vo.setUserpwd(v.getUserpwd());
		
		
		return "modify";
	}
	
	@RequestMapping(value="member/delete.do",method={RequestMethod.POST,RequestMethod.GET})
	   public Object delete(MemberVo vo){ //userid만 들어옴(setter 실행.)
		ModelAndView mv = new ModelAndView();
		int r = dao.delete(vo);
		String msg = "";
		if(r>0){
			msg = "삭제 완료";
		}else
			msg = "삭제중 오류 발생";
		mv.addObject("msg",msg);
		mv.setViewName("delete"); //msg 값이 input_result로 값이 넘어감
		
		return mv;
	}
	
	@RequestMapping(value="member/modify_result.do",method={RequestMethod.POST,RequestMethod.GET})
	   public Object modify_result(MemberVo vo){ //userid만 들어옴(setter 실행.)
		ModelAndView mv = new ModelAndView();
		int r = dao.modify(vo);
		System.out.println(vo.getModifyId());
		System.out.println(vo.getUserid());
		System.out.println(vo.getUserpwd());
		String msg = "";
		if(r>0){
			msg="정상적으로 수정 되었습니다.";
		}else
			msg="수정중 오류 발샐";
		
		mv.addObject("msg",msg);
		mv.setViewName("modify_result");
		return mv;
	}
	
	@RequestMapping(value="member/login.do", method={RequestMethod.POST,RequestMethod.GET})
	   public Object login(MemberVo vo){ //userid만 들어옴(setter 실행.)
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login");
		
		return mv;
	}
	
	@RequestMapping(value="member/loginR.do", method={RequestMethod.POST})
	public Object loginR(MemberVo vo, Errors errors, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		new LoginValidator(dao).validate(vo, errors);
		
		if(errors.getErrorCount()>0) {
			List<ObjectError> list = errors.getAllErrors();
			
			for(ObjectError oe : list) {
				FieldError fe = (FieldError) oe;
				mv.addObject(fe.getField(), fe.getCode());
			}
			mv.setViewName("login");
			
		} else {
			session.setAttribute("userid", vo.getUserid());
			mv.setViewName("login_result");
		}
		
		return mv;
	}
	
	
	
	
}