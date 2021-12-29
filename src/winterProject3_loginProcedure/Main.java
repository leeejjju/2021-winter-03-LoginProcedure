package winterProject3_loginProcedure;
import java.awt.Color;
import java.awt.Font;
import java.util.regex.Pattern;

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
    
    static String ID = "User";
    static String PW;
    static boolean[] errors = {true, true, true}; //차례로 아이디오류, 비번오류, 생년월일오류 
    
    static JFrame frame ; 
    static JTextField getID = new JTextField(60);
    static JPasswordField getPW = new JPasswordField(60);
    
    static String inputID, inputPW, inputBirth, inputGen, inputEM;
    static boolean flag = true; //허용 
    
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
		          makeMainTab(); //메인탭을 소환하고 턴을 마친다
	          }
	          //부적합, 팝업 
	          else {
	        	 JFrame pop = new JFrame(); //팝업창 
	        	 JOptionPane.showMessageDialog(pop, "Unable to login\nPlease check your ID or Password");
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
		
		frame = new JFrame("Main"); //새로운 프레임 생성 
		frame.setSize(1080, 720); //프레임의 사이즈 설정
		frame.setResizable(false);//사용자가 임의로 프레임의 크기를 변경시킬 수 있는가>> 앙대
		frame.setLocationRelativeTo(null);//화면의 어느 위치에서 첫 등장할지>> null이면 자동 센터지정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 닫으면 프로그램도 같이 종료.
		frame.setLayout(null); //레이아웃설정
		
		JLabel welcome = new JLabel("Welcome, "+ ID + " :D"); //라벨로 설명 
		welcome.setFont(new Font("SanSerif", Font.BOLD, 50));
		welcome.setBounds(50,10, 1000, 300);
		frame.add(welcome); //라벨 프레임에 넣기
		
		//로그아웃
		JButton exit = new JButton("로그아웃");
		exit.setBackground(Color.white);
		exit.setBounds(50, 300, 400, 50);
		frame.add(exit);
		exit.addActionListener(ActionListner ->{
			frame.setVisible(false); //메인창 닫기
			getID.setText("");
			getPW.setText("");
	        makeLogInTab(); //로그인창 소환
		});
		
		//회원탈퇴 
		JButton quit = new JButton("계정탈퇴");
		quit.setBackground(Color.white);
		quit.setBounds(50, 360, 400, 50);
		frame.add(quit);
		quit.addActionListener(ActionListner ->{
			
			JFrame pop = new JFrame(); //팝업창 
       	    JOptionPane.showMessageDialog(pop, "회원탈퇴가 완료되었습니다. ");
            
			frame.setVisible(false); //메인창 닫기
			getID.setText("");
			getPW.setText("");
	        makeLogInTab(); //로그인창 소환
		});
		
		
		//회원정보 수정
		
		Font info = new Font("Times", Font.ITALIC, 12);
		//아이디
		JLabel giveMeID = new JLabel("New ID: ");
	    giveMeID.setBounds(600,100,100,50);
	    getID.setBounds(690,115,270,25);
	    JLabel IDinfo = new JLabel(" ※이미 존재하는 ID입니다.");
	    IDinfo.setFont(info);
	    IDinfo.setForeground(Color.red);
	    IDinfo.setBounds(690,140,270,30);
	    frame.add(IDinfo);
	    
	    
		//패스워드
	    JLabel giveMePW = new JLabel("Password: ");
	    giveMePW.setBounds(600,200,100,50);
	    JTextField getPW = new JTextField();
	    getPW.setBounds(690,215,270,25);
	    
	    JLabel PWinfo = new JLabel(" ※영문자/숫자/기호가 포함되어야 합니다.");
	    PWinfo.setFont(info);
	    PWinfo.setForeground(Color.red);
	    PWinfo.setBounds(690,240,270,30);
	    frame.add(PWinfo);
	    
	    
		//생년월일
	    JLabel giveMeBirth = new JLabel("Birthday: ");
	    giveMeBirth.setBounds(600,300,100,50);
	    JTextField getBirth = new JTextField();
	    getBirth.setBounds(690,315,270,25);
	    JLabel BIRinfo = new JLabel(" ※숫자 8자로 입력해주세요. (ex.19991231)");
	    BIRinfo.setFont(info);
	    BIRinfo.setForeground(Color.red);
	    BIRinfo.setBounds(690,340,270,30);
	    frame.add(BIRinfo);
		
		
		//성별
	    JLabel giveMeGender = new JLabel("Gender: ");
	    giveMeGender.setBounds(600,400,100,50);
	    JTextField getGender = new JTextField();
	    getGender.setBounds(690,415,270,25);
	    /*
	    JLabel GENinfo = new JLabel(" ※딱히할말없을듯?");
	    GENinfo.setFont(info);
	    GENinfo.setForeground(Color.red);
	    GENinfo.setBounds(120,140,270,30);
	    frame.add(GENinfo);
		*/
		
		//메일주소
	    JLabel giveMeEMail = new JLabel("E-mail adress: ");
	    giveMeEMail.setBounds(600,500,100,50);
	    JTextField getEMail = new JTextField();
	    getEMail.setBounds(690,515,270,25);
	    /*
	    JLabel EMinfo = new JLabel(" ※이것도딱히..");
	    EMinfo.setFont(info);
	    EMinfo.setForeground(Color.red);
	    EMinfo.setBounds(120,140,270,30);
	    frame.add(EMinfo);
		*/
	    
	    IDinfo.setVisible(false);
	    PWinfo.setVisible(false);
	    BIRinfo.setVisible(false);
	      
	      frame.add(giveMeID);
	      frame.add(getID);
	      frame.add(giveMePW);
	      frame.add(getPW);	 
	      frame.add(giveMeBirth);
	      frame.add(getBirth);	
	      frame.add(giveMeGender);
	      frame.add(getGender);	
	      frame.add(giveMeEMail);
	      frame.add(getEMail);	
	      
	    //가보자고 버튼
	      JButton OK = new JButton("Confirm");
	      OK.setBounds(650,590,300,50);
	      OK.setBackground(Color.PINK);
	      frame.add(OK); 

		frame.setVisible(true); //보여라 얍.. 

	}

	//회원가입 페이지~
	public static void makeSignUpTab() {
		
		frame = new JFrame("Sign Up"); //새로운 프레임 생성 
		frame.setSize(600, 720); //프레임의 사이즈 설정
		frame.setResizable(false);//사용자가 임의로 프레임의 크기를 변경시킬 수 있는가>> 앙대
		frame.setLocationRelativeTo(null);//화면의 어느 위치에서 첫 등장할지>> null이면 자동 센터지정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 닫으면 프로그램도 같이 종료.
		frame.setLayout(null); //레이아웃설정
		
		//라벨, 회원가입
		JLabel title = new JLabel("Sign Up"); 
		title.setFont(new Font("Sanif", Font.BOLD, 50));
		title.setBounds(50, -100, 1000, 300);
		frame.add(title); //라벨 프레임에 넣기
	    
		Font info = new Font("Times", Font.ITALIC, 12);
		
		//아이디: 중복체크 
		JLabel giveMeID = new JLabel("New ID: ");
	    giveMeID.setBounds(30,100,100,50);
	    getID.setBounds(120,115,270,25);
	    JLabel IDinfo = new JLabel(" ※이미 존재하는 ID입니다.");
	    IDinfo.setFont(info);
	    IDinfo.setForeground(Color.red);
	    IDinfo.setBounds(120,140,270,30);
	    frame.add(IDinfo);
	    IDinfo.setVisible(false);
	    
	    
		//패스워드: 조건체크
	    JLabel giveMePW = new JLabel("Password: ");
	    giveMePW.setBounds(30,200,100,50);
	    JTextField getPW = new JTextField();
	    getPW.setBounds(120,215,270,25);
	    JLabel PWinfo = new JLabel(" ※영문자/숫자/기호가 포함되어야 합니다.");
	    PWinfo.setFont(info);
	    PWinfo.setForeground(Color.red);
	    PWinfo.setBounds(120,240,270,30);
	    frame.add(PWinfo);
	    PWinfo.setVisible(false);
	    
	    
		//생년월일: 숫자only
	    JLabel giveMeBirth = new JLabel("Birthday: ");
	    giveMeBirth.setBounds(30,300,100,50);
	    JTextField getBirth = new JTextField();
	    getBirth.setBounds(120,315,270,25);
	    JLabel BIRinfo = new JLabel(" ※숫자 8자로 입력해주세요. (ex.19991231)");
	    BIRinfo.setFont(info);
	    BIRinfo.setForeground(Color.red);
	    BIRinfo.setBounds(120,340,270,30);
	    frame.add(BIRinfo);
	    BIRinfo.setVisible(false);
		
		
		//성별: 체크박스? 
	    JLabel giveMeGender = new JLabel("Gender: ");
	    giveMeGender.setBounds(30,400,100,50);
	    JCheckBox getGender1 = new JCheckBox("Mail", true);
	    JCheckBox getGender2 = new JCheckBox("Femail", false);
	    //JTextField getGender = new JTextField();
	    getGender1.setBounds(120,415,100,25);
	    getGender2.setBounds(220,415,100,25);
	    
		
		//메일주소: 도메인 선택
	    JLabel giveMeEMail = new JLabel("E-mail adress: ");
	    giveMeEMail.setBounds(30,500,100,50);
	    JTextField getEMail = new JTextField();
	    getEMail.setBounds(120,515,270,25);
	    /*
	    JLabel EMinfo = new JLabel(" ※이것도딱히..");
	    EMinfo.setFont(info);
	    EMinfo.setForeground(Color.red);
	    EMinfo.setBounds(120,140,270,30);
	    frame.add(EMinfo);
		*/

	      //가보자고 버튼
	      JButton OK = new JButton("Sign Up");
	      OK.setBounds(70,600,300,50);
	      OK.setBackground(Color.PINK);
	      frame.add(OK);  
	      
	      JButton SU = new JButton("setErrors");
	      SU.setBounds(400,600,50,50);
	      SU.setBackground(Color.LIGHT_GRAY);
	      frame.add(SU);
	     
	      
	      frame.add(giveMeID);
	      frame.add(getID);
	      frame.add(giveMePW);
	      frame.add(getPW);	 
	      frame.add(giveMeBirth);
	      frame.add(getBirth);	
	      frame.add(giveMeGender);
	      frame.add(getGender1);	
	      frame.add(getGender2);	
	      frame.add(giveMeEMail);
	      frame.add(getEMail);	
	      
	      frame.setVisible(true); //보여라 얍.. 
          
	      
	      //static String inputID, inputPW, inputBirth, inputGen, inputEM;

	      
	      //버튼기능연동, 확인버튼
	      OK.addActionListener(event ->{
	    	  //오류상태 리셋하고 
		      flag = true; //허용 
		      errors[0] = true;
		      errors[1] = true;
		      errors[2] = true;
		      IDinfo.setVisible(false);
		      PWinfo.setVisible(false);
		      BIRinfo.setVisible(false);
	    	  
	    	  inputID = getID.getText();
	    	  inputPW = getPW.getText();
	    	  inputBirth = getBirth.getText();
	    	  inputGen = (true)? "Mail" : "Femail"; //조건식넣어주기@@@@@@@@@@@@@@@@
	    	  inputEM = getEMail.getText() + "선택한도메인"; //도메인넣어주기@@@@@@@@@@@@@@@@@@
	    	  
	    	  //아이디 중복체크
	    	  //@@@@@@@@@@@@@@@@@@@@@이거어케하누 
	    	  
	    	  //패스워드 조건체크
	    	  for(int i = 0; i < inputPW.length(); i++) {
	    		  
	    		  //숫자있니
	    		  if(Character.isDigit(inputPW.charAt(i))) {
	    			  errors[0] = false;
	    		  }
	    		  //문자있니
		    	  else if(Character.isUpperCase(inputPW.charAt(i)) || Character.isLowerCase(inputPW.charAt(i))) {
	    			  errors[1] = false;
	    		  }
		    	  //숫자도문자도아닌거 있니
		    	  else {
	    			  errors[2] = false;
	    		  }
	    		  
	    	  }
	    	  
	    	  if(errors[0] || errors[1] || errors[2]) {
	    		  flag = false; //하나라도 충족 안됐으면 플래그 내리기 
	    		  PWinfo.setVisible(true);
	    	  }
	    	  
	    	  //생년월일 조건체크, 숫자아닌게 껴있거나 8글자가 아닌경우 
	    	  if(!Pattern.matches("^[0-9]*$", inputBirth) || (inputBirth.length() != 8)) {
	    		  flag = false; //오류.. 
    			  BIRinfo.setVisible(true); //경고안내 보이게하기 
	    	  }


	    	  //에러없으면, DB에 정보저장, 메인탭으로 
	          if(flag) {
	        	  //db에정보저장!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	        	  ID = inputID;
	        	  frame.setVisible(false); //회원가입 창 닫기
		          makeMainTab(); //로그인탭을 소환하고 턴을 마친다
	          }
	          //에러있음, 팝업
	          else {
	        	  
		          JFrame pop = new JFrame(); //팝업창
	        	  JOptionPane.showMessageDialog(pop, "Unable to create account\nPlease check some conditions");
	        	  
	          }
	       });
	      
	      SU.addActionListener(event ->{
	          flag = !flag;
	       });
	      
	      
	}

	
	//메인~ 그저실행함 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		makeLogInTab();
		//DataBase.loadDataBase();
		//test
	}

}
