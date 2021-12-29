package winterProject3_loginProcedure;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
/*
 *정보
 2021-12-28-18:00~ 2021-12-30-18:00
 2021-winter 과제3, 데이터베이스- 시스템 로그인 기능 만들기 
 HGU 전산전자공학부_ 22100579 이진주
 */

/*
1. 로그인 프레임 만들기
2. 버튼 기능 연동.. 
	2-1 로그인 -> 적합판정 후 적합시 페이지로, 부적합시 팝업. 
	2-2 회원가입 ->회원가입 페이지로
3. 페이지 만들기
	3-1 메인페이지
		3-1-1 회원정보 수정
		3-1-2 계정탈퇴
	3-2 회원가입 페이지
		3-2-1 입력받는 창... 
		3-2-2 가입완료시 적합판정 후 적합시 로그인창으로, 부적합시 팝업. 
	
*/

public class Main {
    
    static String ID;
    static String PW;
    static boolean errors = true;
    
    static JFrame frame ; 
    static JTextField getID = new JTextField(60);
    static JPasswordField getPW = new JPasswordField(60);
    
	//로그인창~ 
	public static void makeLogInTab() {
		
		frame = new JFrame("Log In"); //새로운 프레임 생성 
		
		frame.setSize(400, 200); //프레임의 사이즈 설정
		frame.setResizable(false);//사용자가 임의로 프레임의 크기를 변경시킬 수 있는가>> 앙대
		frame.setLocationRelativeTo(null);//화면의 어느 위치에서 첫 등장할지>> null이면 자동 센터지정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 닫으면 프로그램도 같이 종료.
		frame.setLayout(null); //레이아웃설정
		
		JLabel Title = new JLabel("Input your ID and Password :D"); //라벨로 설명 
	    Title.setBounds(30, 0, 300, 50);
	    frame.add(Title); //라벨 프레임에 넣기
	      
	      //입력창과 라벨과 버튼(로그인, 회원가입)
	      JLabel giveMeID = new JLabel("ID: ");
	      giveMeID.setBounds(30,40,100,50);
	      JLabel giveMePW = new JLabel("PW: ");
	      giveMePW.setBounds(30,65,100,50);
	      getID.setBounds(70,50,270,25);
	      getPW.setBounds(70,80,270,25);
	      
	      JButton OK = new JButton("Log In");
	      OK.setBounds(70,110,133,30);
	      OK.setBackground(Color.PINK);
	      JButton SU = new JButton("Sign Up");
	      SU.setBounds(205,110,132,30);
	      SU.setBackground(Color.LIGHT_GRAY);
	      
	      frame.add(giveMeID);
	      frame.add(giveMePW);
	      frame.add(getID);
	      frame.add(getPW);	 
	      frame.add(OK);   
	      frame.add(SU);
	      
	      frame.setVisible(true); //보여라 얍.. 
          
	      //버튼기능연동, 확인버튼
	      OK.addActionListener(event ->{
	          ID = getID.getText();
	          PW = getPW.getText();
	          
	          //적합, 페이지로 
	          //select * from user where "username" like '[유저이름]';
	          if("asas".equals(ID)) {
	        	  frame.setVisible(false); //로그인창 닫기
		          System.out.println("Wellcome, "+ ID + " :D"); //따땃한 말한마디
		          makeMainTab(); //메인탭을 소환하고 턴을 마친다
	          }
	          //부적합, 팝업 
	          else {
	        	 JFrame pop = new JFrame(); //팝업창 
	        	 JOptionPane.showMessageDialog(pop, "Unable to login\nPlease check your ID or Password");
	             System.out.println("떙");
	          }
	       });
	      
	      //버튼기능연동, 회원가입버튼
	      SU.addActionListener(event ->{
	    	  
	          frame.setVisible(false); //로그인창 닫기
		      makeSignUpTab(); //메인탭을 소환하고 턴을 마친다
	          
	       });
	      
	      
	}

	//메인페이지~ 
	public static void makeMainTab() {
		
		
		
		JFrame main = new JFrame("Main"); //새로운 프레임 생성 
		main.setSize(1080, 720); //프레임의 사이즈 설정
		main.setResizable(false);//사용자가 임의로 프레임의 크기를 변경시킬 수 있는가>> 앙대
		main.setLocationRelativeTo(null);//화면의 어느 위치에서 첫 등장할지>> null이면 자동 센터지정 
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 닫으면 프로그램도 같이 종료.
		main.setLayout(null); //레이아웃설정
		
		JLabel welcome = new JLabel("Wellcome, "+ ID + " :D"); //라벨로 설명 
		welcome.setFont(new Font("SanSerif", Font.BOLD, 50));
		welcome.setBounds(50,10, 1000, 300);
	    main.add(welcome); //라벨 프레임에 넣기
	      
	    /*
	      //입력창과 라벨과 버튼(로그인, 회원가입)
	      JLabel giveMeID = new JLabel("ID: ");
	      giveMeID.setBounds(30,40,100,50);
	      JLabel giveMePW = new JLabel("PW: ");
	      giveMePW.setBounds(30,65,100,50);
	      JTextField getID = new JTextField(60);
	      getID.setBounds(70,50,270,25);
	      JTextField getPW = new JTextField(60);
	      getPW.setBounds(70,80,270,25);
	      
	      JButton OK = new JButton("Log In");
	      OK.setBounds(70,110,133,30);
	      OK.setBackground(Color.PINK);
	      JButton SU = new JButton("Sign Up");
	      SU.setBounds(205,110,132,30);
	      SU.setBackground(Color.LIGHT_GRAY);
	      
	      logIn.add(giveMeID);
	      logIn.add(giveMePW);
	      logIn.add(getID);
	      logIn.add(getPW);	 
	      logIn.add(OK);   
	      logIn.add(SU);
	      */
	    main.setVisible(true); //보여라 얍.. 
	      /*
	      //버튼기능연동, 확인버튼
	      OK.addActionListener(event ->{
	          
	          String ID;
	          String PW;
	          
	          //적합, 페이지로 
	          if(true) {
	        	  
	        	  logIn.setVisible(false); //로그인창 닫기
		          System.out.println("Wellcome, "+ ID + " :D"); //따땃한 말한마디
		          new GraphicEditor(title, sizeX, sizeY); //그림판을 소환하고 턴을 마친다
	          }
	          //부적합, 팝업 
	          else {
	             
	          }

	    
	       });
	      */
	}

	//회원가입 페이지~
	public static void makeSignUpTab() {
		
		frame = new JFrame("Sign Up"); //새로운 프레임 생성 
		
		frame.setSize(800, 800); //프레임의 사이즈 설정
		frame.setResizable(false);//사용자가 임의로 프레임의 크기를 변경시킬 수 있는가>> 앙대
		frame.setLocationRelativeTo(null);//화면의 어느 위치에서 첫 등장할지>> null이면 자동 센터지정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 닫으면 프로그램도 같이 종료.
		frame.setLayout(null); //레이아웃설정
		
		JLabel Title = new JLabel("Input your ID and Password :D"); //라벨로 설명 
	    Title.setBounds(30, 0, 300, 50);
	    frame.add(Title); //라벨 프레임에 넣기
	      
	      //입력창과 라벨과 버튼(로그인, 회원가입)
	      JLabel giveMeID = new JLabel("ID: ");
	      giveMeID.setBounds(30,40,100,50);
	      JLabel giveMePW = new JLabel("PW: ");
	      giveMePW.setBounds(30,65,100,50);
	      getID.setBounds(70,50,270,25);
	      getPW.setBounds(70,80,270,25);
	      
	      JButton OK = new JButton("Log In");
	      OK.setBounds(70,110,133,30);
	      OK.setBackground(Color.PINK);
	      JButton SU = new JButton("Sign Up");
	      SU.setBounds(205,110,132,30);
	      SU.setBackground(Color.LIGHT_GRAY);
	      
	      frame.add(giveMeID);
	      frame.add(giveMePW);
	      frame.add(getID);
	      frame.add(getPW);	 
	      frame.add(OK);   
	      frame.add(SU);
	      
	      frame.setVisible(true); //보여라 얍.. 
          
	      //버튼기능연동, 확인버튼
	      OK.addActionListener(event ->{
	          
	          //적합, 로그인탭으로
	          //select * from user where "username" like '[유저이름]';
	          if(!errors) {
	        	  frame.setVisible(false); //회원가입 창 닫기
		          makeLogInTab(); //로그인탭을 소환하고 턴을 마친다
	          }
	          //부적합, 팝업 
	          else {
	        	 JFrame pop = new JFrame(); //팝업창 
	        	 JOptionPane.showMessageDialog(pop, "Unable to create account\nPlease check some conditions again");
	             System.out.println("떙");
	          }

	    
	       });
	      
	}

	
	//메인~ 그저실행함 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		makeLogInTab();
		//test
	}

}
