package mvc;

public class MemberVo {

	String userid="";
	String userpwd="";
	String modifyId="";
	String email="";
	String confirmpwd="";
	String msg = "";
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public String getConfirmpwd() {
		return confirmpwd;
	}
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	public boolean isPwdEqual(){
		if(this.userpwd.equals("") || this.confirmpwd.equals(""))
			return false;
		else if(this.userpwd.equals(this.confirmpwd))
			return true;
		return false;
	}
	
	
}