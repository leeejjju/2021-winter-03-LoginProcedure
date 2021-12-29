package winterProject3_loginProcedure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoDao {
	
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	//**모든건 키값, id 기준으로! 
	private final String MEMBER_INSERT = "INSERT INTO t1 (ID, PW, BIRTH, GENDER, EMAIL) values (?, ?, ?, ?, ?)"; //모든정보
	private final String MEMBER_UPDATE = "UPDATE t1 SET ID = ?, PW = ?, BIRTH = ?, GENDER = ?, EMAIL = ? WHERE ID = ?"; //검색원하는 필드, 거기에 넣을 값, 아이디 
	private final String MEMBER_DELETE = "DELETE FROM t1 [WHERE ID = ?]"; //아이디
	private final String MEMBER_LOADMEMBER = "SELECT * FROM t1 [WHERE ID = ?]"; //아이디 
	private final String MEMBER_LOADALLMEMBER = "SELECT * FROM t1 order by ID desc";
	private final String MEMBER_LOGINPW = "SELECT PW FROM t1 [WHERE ID = ?]"; //해당 아이디에 매칭된 패스워드 반환..헀으면좋겟다 
	
	//데이터베이스에 새로운 인간 추가하는 메서드
	public void insertMember (Datatata e) {
		try {
			//통로연결..??
			con = DataBase.getConnection();
			//연결형식?을 정해주는건가 하고 궁예해봄 
			stmt = con.prepareStatement(MEMBER_INSERT);
			//입력받은 값으로 스트링 구멍 채우기 
			stmt.setString(1, e.getID());
			stmt.setString(2, e.getPW());
			stmt.setInt(3, e.getBIRTH());
			stmt.setString(4, e.getID());
			stmt.setString(5, e.getID());
			//DB에 저장해주기 
			stmt.executeLargeUpdate(); 
			//다썼으니 닫아주기 
			stmt.close();
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//데이터베이스에 특정인간 정보 업데이트하는 메서드 (덮어쓰기 개념)
	public void updateMember (Datatata e) {
		try {
			//통로연결..??
			con = DataBase.getConnection();
			stmt = con.prepareStatement(MEMBER_UPDATE);
			//입력받은 값으로 스트링 구멍 채우기 
			stmt.setString(1, e.getID());
			stmt.setString(2, e.getPW());
			stmt.setInt(3, e.getBIRTH());
			stmt.setString(4, e.getID());
			stmt.setString(5, e.getID());
			//DB에 저장해주기 
			stmt.executeLargeUpdate(); 
			//연결해제
			stmt.close();
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//데이터베이스에서 특정인간 정보 삭제하는 메서드
	public void deleteMember (Datatata e) {
		try {
			//통로연결..??
			con = DataBase.getConnection();
			stmt = con.prepareStatement(MEMBER_DELETE);
			//입력받은 값으로 스트링 구멍 채우기 
			stmt.setString(1, e.getID());
			//DB에 저장해주기 
			stmt.executeLargeUpdate(); 
			//연결해제
			stmt.close();
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//아이디는 변경불가하게 해야하는걸까.......??? @@@@@@@@@@@@@@@
	//특정 아이디의, 데이터베이스 속 정보 얻어다 야무지게 채운 인스턴트 반환 메서드 
	public Datatata LoadMember (String id) {
			
		Datatata e = null;
			try {
				//통로연결..??
				con = DataBase.getConnection();
				stmt.setString(1, id);
				//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
				rs = stmt.executeQuery(MEMBER_LOADMEMBER);
				
				//datatata에다가 값 읽어다가 쇽쇽 넣어주기 
				e.setID(rs.getString("ID"));
				e.setPW(rs.getString("PW"));
				e.setBIRTH(rs.getInt("BIRTH"));
				e.setGENDER(rs.getString("GENDER"));
				e.setEMAIL(rs.getString("EMAIL"));
				
				//다썼으니 닫아주기 ?이거 필요하려나 
				stmt.close();
				
				//차곡차곡 담은 꾸러미 반환하기 
				return e;
				
			}catch(SQLException e1) {
				e1.printStackTrace();
				return e;
			}
			
		}
	
	//데이터베이스에서 특정 인간의 정보 가져와서 인스턴트에 담는..? 메서드 
	public Datatata[] LoadAllMember () {
				Datatata[] e = null;
				try {
						//통로연결..??
						con = DataBase.getConnection();
						//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
						rs = stmt.executeQuery(MEMBER_LOADALLMEMBER);
						
						//datatata에다가 값 읽어다가 쇽쇽 넣어주기, 끝까지~ 
						int i = 0;
						
						while(rs.next()) {
							e[i].setID(rs.getString("ID"));
							e[i].setPW(rs.getString("PW"));
							e[i].setBIRTH(rs.getInt("BIRTH"));
							e[i].setGENDER(rs.getString("GENDER"));
							e[i].setEMAIL(rs.getString("EMAIL"));
							i++;
						}
						
						//다썼으니 닫아주기 ?이거 필요하려나 
						stmt.close();
						
						//차곡차곡 담은 꾸러미 리스트 반환하기 
						return e;
						
				}catch(SQLException e1) {
						e1.printStackTrace();
						return e;
				}	
				
		}
	
	//특정 아이디와 연동된 비밀번호 얻어내는 메서드 
	public String GetPW (String id) {
			String pw = "";
			try {
				//통로연결..??
				con = DataBase.getConnection();
				stmt.setString(1, id);
				//읽어올범위..?인걸까?머를읽어올지? 모르겟음 이거 형태는 result임 
				rs = stmt.executeQuery(MEMBER_LOGINPW);
				
				//반환된..패스워드 스트링에 저장해서  
				pw = rs.getString("PW");
				
				//다썼으니 닫아주기 ?이거 필요하려나 
				stmt.close();
				
				//얻어낸 패스워드 반환 
				return pw;
				
			}catch(SQLException e1) {
				e1.printStackTrace();
				return pw;
			}
			
			
		}
	
	

}




