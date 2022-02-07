package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DTO.MemberDTO;



public class MemberDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ArrayList<MemberDTO> memArrayList;	
	Scanner sc=new Scanner(System.in);
	 
	 public MemberDAO() {	
			memArrayList=new ArrayList<MemberDTO>();		
			try {
				String user="system";
				String pw="1234";
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				try {
					con=DriverManager.getConnection(url,user,pw);
					st=con.createStatement();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
	
	//member 테이블 내용 추가
	public ArrayList<MemberDTO> Search() {	
		memArrayList=new ArrayList<MemberDTO>();
			String SQL="select * from member";			
			try {	
				pstmt=con.prepareStatement(SQL);
				rs=st.executeQuery(SQL);
				while(rs.next()) {
					String ID=rs.getString("ID");
					String password=rs.getString("password");				
					String name=rs.getString("name");				
					String address=rs.getString("address");
					MemberDTO vo=new MemberDTO(ID,password,name,address);
					memArrayList.add(vo);
					pstmt.executeUpdate();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}		 
		return memArrayList;
	}
	
	public boolean login(String ID,String password) {
		boolean check=false;
		memArrayList=new ArrayList<MemberDTO>();
			String SQL="select password from member where ID=?";			
			try {
				System.out.print("아이디를 입력하세요: ");
				ID=sc.next();
				System.out.print("패스워드를 입력하세요: ");
				password=sc.next();				
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1,ID);
				rs = pstmt.executeQuery(); 
				if (rs.next()) {
					if (rs.getString(1).contentEquals(password)) {
						System.out.println("로그인 성공!");
						return check=true; // 로그인 성공
					}
					else {
						return check; // 비밀번호 불일치
					}
				}
				return check; // 아이디가 없음
			} catch (Exception e) {
				e.printStackTrace();
			}
			return check; // DB 오류 
		}
			
	//회원가입
	public ArrayList<MemberDTO> InsertMemberInfo(String ID,String password,String name,String address) {
		String SQL="insert into member(ID,PASSWORD,NAME,ADDRESS) "
				+ "values(?,?,?,?)";		
		try {
			System.out.print("아이디 입력:");
			ID=sc.next();
			System.out.print("비밀번호 입력:");
			password=sc.next();
			System.out.print("이름 입력:");
			name=sc.next();
			System.out.print("주소 입력:");
			address=sc.next();	
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, address);			
			pstmt.executeQuery();			
			MemberDTO vo=new MemberDTO(ID,password,name,address);
			memArrayList.add(vo);
			System.out.println(vo.getName()+"님 가입을 환영합니다!");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return memArrayList;
	}
	
	//회원정보 수정
	public ArrayList<MemberDTO> updateMemberInfo(String ID,String password,String address) {
		String SQL="update member set address=? where ID=? and password=?";				
		try {
		System.out.print("정보를 수정할 이이디를 입력하세요:");
		ID=sc.next();
		System.out.print("정보를 수정할 비밀번호를 입력하세요:");
		password=sc.next();				
		System.out.print("변경할 주소를 입력하세요:");
		address=sc.next();							
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, address);
			pstmt.setString(2, ID);	
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			MemberDTO vo=new MemberDTO(ID,password,address);
			for(int i=0;i<memArrayList.size();i++) {
				if(memArrayList.get(i).getID().equals(ID)&&memArrayList.get(i).getPassword().equals(password)) {
					memArrayList.set(i, vo);
				}
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return memArrayList;
	}
	
	//회원정보 삭제
	public ArrayList<MemberDTO> deleteMemberInfo(String ID,String password) {
		String SQL="delete from member where ID=? and password=?";
		boolean check=false;
		try {
			System.out.print("정보를 삭제할 아이디를 입력하세요:");
			ID=sc.next();
			System.out.print("비밀번호를 입력하세요:");
			password=sc.next();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			MemberDTO vo=new MemberDTO(ID,password);
			for(int i=0;i<memArrayList.size();i++) {
			if(memArrayList.get(i).getID().equals(ID)&&memArrayList.get(i).getPassword().equals(password)) {
			System.out.println(memArrayList.get(i).getName()+"님의 정보가 삭제됩니다!");	
			check=true;
			memArrayList.remove(vo);			
			}
			}	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		if (check==false) {
			System.out.println("ID 혹은 비밀번호가 잘못 되었습니다.");
		}
		return memArrayList;		
	}
	
	public int getMemIndexid(String ID) {		
		memArrayList=Search();
		int j=0;
		for(MemberDTO mem:memArrayList) {
			if(mem.getID().equals(ID)==true) {
				return j;
			}
			j++;
		}
		return -1;
	}
	
	public int getMemIndexps(String password) {		
		memArrayList=Search();
		int j=0;
		for(MemberDTO mem:memArrayList) {
			if(mem.getPassword().equals(password)==true) {
				return j;
			}
			j++;
		}
		return -1;
	}	
	
	
}
