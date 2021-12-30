package winterProject3_loginProcedure;
import java.sql.*;

public class Test {

	public static void main(String[] args) {
		
		Connection con = null; //커넥터
		String dbFileUrl = "jdbc:sqlite:./data/test.db";
		
		try {
			
			//SQLite JDBM 체크
			Class.forName("org.sqlite.JDBC"); //SQLite를 사용하겠다는 문장
			
			//SQLite 데이터베이스 파일에 연결 
			//String dbFile = "C:\\Java\\workspace\\winterProject3_loginProcedure\\data\\test.db"; //파일위치..경로 나타낸 문자열 
			//con = DriverManager.getConnection("jdbc:sqlite"+dbFile); //커넥터에 파일경로로 db 연결?
			con = DriverManager.getConnection(dbFileUrl); //커넥터에 파일 링크로 db드라이브 연결?
			
			
			//SQL 수행
			Statement stat = con.createStatement(); //아마 커넥터로 db접근과 관련한 무언가의 일을...
			ResultSet rs = null; //이게머지.. 
			

			
			//헉 추가됏다!!!!!! 
			rs = stat.executeQuery("select * from table1;");
			
			//stat.executeUpdate("insert into table1(id, pw) values('Kang', 1234)");
			stat.executeUpdate("insert into table1(id, pw) values('Hong', 1234)");
			System.out.println("SQLite DB connected"); //연결 확인을 위한 출력문 
			
			rs = stat.executeQuery("SELECT id, pw FROM table1 ");
			while(rs.next()) {
				String ID = rs.getString("id");
				String PW = rs.getString("pw");
				
				System.out.println(ID+"			"+PW);
			}
			System.out.println("해치웠나");
			
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(con != null) {
				try {con.close();}catch(Exception e) {}
			}
		}
	}

}
