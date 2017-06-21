package mvc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	Connection conn;
	
	String driver;
	String url;
	String user;
	String pwd;
	

	public DBConnect() {}

	public Connection getConn() {
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
