package di_step4;

import org.springframework.context.support.GenericXmlApplicationContext;

// 내부 아키텍처는 오픈되면 안되는 개념
//객체 생성은 외부 조립기에 의해서 생성한뒤 반환 받도록 조치

public class DiMain {
	
	public static void prn(MyDB db) {
		System.out.println(db.getDB());
		
	}

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("di_step4/assembler.xml");
		
		MyDB db = ctx.getBean("db",Oracle.class);
		prn(db);
	}
}
