package winterProject3_loginProcedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoDao {
	
	DaoDao(){
		conn = DataBase.getConnection();
	}
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	
	//**모든건 키값, id 기준으로! 
	private final String MEMBER_INSERT = "INSERT INTO t1 VALUES (?, ?, ?, ?, ?)"; //모든정보
	private final String MEMBER_UPDATE = "UPDATE t1 SET PW = ?, BIRTH = ?, GENDER = ?, EMAIL = ? WHERE ID = ?"; //검색원하는 필드, 거기에 넣을 값, 아이디 
	private final String MEMBER_DELETE = "DELETE FROM t1 WHERE ID = ?"; //아이디
	private final String MEMBER_LOADMEMBER = "SELECT * FROM t1 WHERE ID = ?"; //아이디 
	private final String MEMBER_LOADALLID = "SELECT * FROM t1";
	private final String MEMBER_LOGINPW = "SELECT PW FROM t1 WHERE ID = ?"; //해당 아이디에 매칭된 패스워드 반환
	
	//데이터베이스에 새로운 인간 추가하는 메서드(완)
	public void insertMember (Datatata e) {
		
		try {
			//통로연결..??
			//conn = DataBase.getConnection();
			//연결형식?을 정해주는건가 하고 궁예해봄 
			stmt = conn.prepareStatement(MEMBER_INSERT);
			//입력받은 값으로 스트링 구멍 채우기 
			stmt.setString(1, e.getID());
			stmt.setString(2, e.getPW());
			stmt.setString(3, e.getBIRTH());
			stmt.setString(4, e.getID());
			stmt.setString(5, e.getID());
			//DB에 저장해주기 
			stmt.executeUpdate();
			//다썼으니 닫아주기 
			stmt.close();
			System.out.println("유저 데이터를 저장했습니다.");
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//데이터베이스에 특정인간 정보 업데이트하는 메서드 (완)
	public void updateMember (Datatata e, String id) {
		try {
			//통로연결..??
			conn = DataBase.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			//입력받은 값으로 스트링 구멍 채우기 
			
			stmt.setString(1, e.getPW());
			stmt.setString(2, e.getBIRTH());
			stmt.setString(3, e.getGENDER());
			stmt.setString(4, e.getEMAIL());
			stmt.setString(5, id);
			//DB에 저장해주기 
			stmt.executeUpdate(); 
			//연결해제
			stmt.close();
			System.out.println("유저 데이터를 업데이트했습니다.");
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//데이터베이스에서 특정인간 정보 삭제하는 메서드(완)
	public void deleteMember (String id) {
		try {
			//통로연결..??
			//conn = DataBase.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			//입력받은 값으로 스트링 구멍 채우기 
			stmt.setString(1, id);
			
			//DB에 저장해주기 
			stmt.executeUpdate(); 
			
			//연결해제
			stmt.close();
			System.out.println("유저 데이터를 삭제했습니다.");
			
		}catch(SQLException e1) {
			e1.printStackTrace();
			//System.out.println("asfasdfasdfasdfasd");
		}
	}
	
	//아이디는 변경불가하게 해야하는걸까.......??? @@@@@@@@@@@@@@@
	//특정 아이디의, 데이터베이스 속 정보 얻어다 야무지게 채운 인스턴트 반환 메서드 (완)
	public Datatata LoadMember (String id) {
			
		Datatata e = new Datatata();
			
			try {
				//통로연결..??
				//conn = DataBase.getConnection();
				stmt = conn.prepareStatement(MEMBER_LOADMEMBER);
				stmt.setString(1, id);
				//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
				rs = stmt.executeQuery(); 
				
				//datatata에다가 값 읽어다가 쇽쇽 넣어주기 
				if(rs.next()) e.setPW(rs.getString("PW"));
				e.setBIRTH(rs.getString("BIRTH"));
				e.setGENDER(rs.getString("GENDER"));
				e.setEMAIL(rs.getString("EMAIL"));
				
				//다썼으니 닫아주기 ?이거 필요하려나 
				stmt.close();
				System.out.println("유저 데이터를 불러왔습니다.");
				
				//차곡차곡 담은 꾸러미 반환하기 
				
				return e;
				
			}catch(SQLException e1) {
				e1.printStackTrace();
				return e;
			}
			
		}
	
	//데이터베이스에서 사용자 id목록 가져오는 메서드 /관리자용/ 
	public ArrayList<String> LoadAllID () {
				//사이즈를 모르채 시작하고 값을 추가해야하므로 리스트형식으로.. 
				ArrayList<String> ids = new ArrayList<String>();
				try {
						//통로연결..??
						//conn = DataBase.getConnection();
						//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
						stmt = conn.prepareStatement(MEMBER_LOADALLID);
						rs = stmt.executeQuery();
						
						//datatata에다가 값 읽어다가 쇽쇽 넣어주기, 끝까지~ 
						
						while(rs.next()) {
							ids.add(rs.getString("ID"));
							
						}
						
						//다썼으니 닫아주기 ?이거 필요하려나 
						stmt.close();
						System.out.println("유저 아이디 목록을 불러왔습니다.");
						
						//차곡차곡 담은 꾸러미 리스트 반환하기 
						return ids;
						
				}catch(SQLException e1) {
						e1.printStackTrace();
						return null;
				}	
				
		}
	
	//특정 아이디와 연동된 비밀번호 얻어내는 메서드 (완)
	public String GetPW (String id) {
			
			String pw = "";
			
			try {
				//통로연결..??
				//conn = DataBase.getConnection(); //중복같아서지워봄
				stmt = conn.prepareStatement(MEMBER_LOGINPW);
				stmt.setString(1, id);
				
				//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
				rs = stmt.executeQuery();
				
				//반환된..패스워드 스트링에 저장해서  
				if(rs.next()) pw = rs.getString("PW");
				
				//다썼으니 닫아주기 ?이거 필요하려나 
				stmt.close();
				
				//얻어낸 패스워드 반환 
				return pw;
				
			}catch(SQLException e1) {
				e1.printStackTrace();
				System.out.println("그런 유저 없습니당");
				return "0";
			}
			
		}
	
	//아이디 중복검사 (완)
	public boolean checkID (String id) {
		
		try {
			stmt = conn.prepareStatement(MEMBER_LOADALLID);
			//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
			rs = stmt.executeQuery(); 
			
			//값 읽어다가 판별... 
			while(rs.next()) {
				//System.out.println(rs.getString("ID"));
				if(rs.getString("ID").equals(id)) { //겹치는거 발견시 
					stmt.close();
					return false;
				}
				rs.next();
			}
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	//액션리스너 안에서 문자열 받아오기우ㅟ한... 그런거... 
	public void getStr(String user1, String user2) {
		user1 = user2;
	}
}




