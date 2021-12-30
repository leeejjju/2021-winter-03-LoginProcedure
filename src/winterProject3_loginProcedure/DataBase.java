package winterProject3_loginProcedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
	
	static Connection con = null; //커넥터
	
	final static String dbFileUrl = "jdbc:sqlite:./data/LogInDB.db";

	public static Connection getConnection(){
		try {
			//SQLite JDBM 체크
			Class.forName("org.sqlite.JDBC"); //SQLite를 사용하겠다는 문장
			con = DriverManager.getConnection(dbFileUrl);
			System.out.println("데이터베이스 연결에 성공했습니다.");
		}catch (Exception e){
			
		}
		return con;
	}
		
}
