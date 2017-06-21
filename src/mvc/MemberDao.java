package mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	Connection conn;
	
	public MemberDao(DBConnect db){
		this.conn = db.getConn();
	}
	
	public MemberVo view(MemberVo vo) {
		MemberVo v = new MemberVo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			rs = ps.executeQuery();
			if (rs.next()) {
				v.setUserpwd(rs.getString("userpwd"));
			} else {
				v.setUserpwd("!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		v.setUserid(vo.getUserid());
		
		return v;
	}
	
	//input.do가 들어오면 여기서 처리
	//입력폼의 내용이 memberVo를 타고 들어옴
	public int input(MemberVo vo){
		int r=0;
		PreparedStatement ps = null;
		
		try{
			String sql = "insert into member(userid,userpwd)"
						+ " values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getUserpwd());
			r = ps.executeUpdate();
			
		}catch(Exception ex){
			r = -1;
			ex.printStackTrace();
		}finally{
			
			return r;
		}
		
	}
	
	public List<MemberVo> list(MemberVo vo) {
	      List<MemberVo> list = new ArrayList<MemberVo>();
	      PreparedStatement ps = null;
	      ResultSet rs = null;

	      try {
	         String sql = "select * from member where userid like ?";//like는 포함된 문자를 출력해줌
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, "%" + vo.getUserid() + "%");//% 포함하는거
	         rs = ps.executeQuery();

	         while (rs.next()) {
	            MemberVo v = new MemberVo();
	            v.setUserid(rs.getString("userid"));
	            v.setUserpwd(rs.getString("userpwd"));
	            list.add(v);

	         }

	      } catch (Exception e) {
	    	  list = null;
	         e.printStackTrace();

	      }

	      return list;

	   
	   }
	
	public MemberVo selectOne(MemberVo vo){
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVo v = new MemberVo();
		
		try{
			String sql = "select * from member where userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			rs = ps.executeQuery();
			
			if(rs.next()){
				v.setUserid(rs.getString("userid"));
				v.setUserpwd(rs.getString("userpwd"));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return v;
	}
	
	public int modify(MemberVo vo){
		 PreparedStatement ps = null;
	     MemberVo temp = selectOne(vo);
	      int r ;
	      try{
	    	  String sql = "update member set userid=?, userpwd=? where userid=?";
	    	  ps = conn.prepareStatement(sql);
	    	  ps.setString(1, vo.getModifyId());
	    	  ps.setString(2, vo.getUserpwd());
	    	  ps.setString(3, vo.getUserid());
	    	  
	    	  r = ps.executeUpdate();
	    	  
	    	  
	      }catch(Exception ex){
	    	  r = 0;
	    	  ex.printStackTrace();
	      }
	      return r;
	}
	
	public int delete(MemberVo vo){
		PreparedStatement ps = null;
		int rs ;
		
		try{
			String sql = "delete from member where userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			rs = ps.executeUpdate();
			
		}catch(Exception ex){
			rs = -1;
			ex.printStackTrace();
		}
		return rs;
	}
	
	public boolean login(MemberVo vo) {
		boolean b = true;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try{
			String sql = "select count(*) cnt from member where userid=? and userpwd=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getUserpwd());
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				b = false;
			}
			
		}catch(Exception e){
			b = false;
			e.printStackTrace();
		}finally {
			return b;
		}
	}
	
	
	
	
	
}