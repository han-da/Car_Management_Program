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
	
	
	//member ���̺� ���� �߰�
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
				System.out.print("���̵� �Է��ϼ���: ");
				ID=sc.next();
				System.out.print("�н����带 �Է��ϼ���: ");
				password=sc.next();				
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1,ID);
				rs = pstmt.executeQuery(); 
				if (rs.next()) {
					if (rs.getString(1).contentEquals(password)) {
						System.out.println("�α��� ����!");
						return check=true; // �α��� ����
					}
					else {
						return check; // ��й�ȣ ����ġ
					}
				}
				return check; // ���̵� ����
			} catch (Exception e) {
				e.printStackTrace();
			}
			return check; // DB ���� 
		}
			
	//ȸ������
	public ArrayList<MemberDTO> InsertMemberInfo(String ID,String password,String name,String address) {
		String SQL="insert into member(ID,PASSWORD,NAME,ADDRESS) "
				+ "values(?,?,?,?)";		
		try {
			System.out.print("���̵� �Է�:");
			ID=sc.next();
			System.out.print("��й�ȣ �Է�:");
			password=sc.next();
			System.out.print("�̸� �Է�:");
			name=sc.next();
			System.out.print("�ּ� �Է�:");
			address=sc.next();	
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, address);			
			pstmt.executeQuery();			
			MemberDTO vo=new MemberDTO(ID,password,name,address);
			memArrayList.add(vo);
			System.out.println(vo.getName()+"�� ������ ȯ���մϴ�!");
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return memArrayList;
	}
	
	//ȸ������ ����
	public ArrayList<MemberDTO> updateMemberInfo(String ID,String password,String address) {
		String SQL="update member set address=? where ID=? and password=?";				
		try {
		System.out.print("������ ������ ���̵� �Է��ϼ���:");
		ID=sc.next();
		System.out.print("������ ������ ��й�ȣ�� �Է��ϼ���:");
		password=sc.next();				
		System.out.print("������ �ּҸ� �Է��ϼ���:");
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
	
	//ȸ������ ����
	public ArrayList<MemberDTO> deleteMemberInfo(String ID,String password) {
		String SQL="delete from member where ID=? and password=?";
		boolean check=false;
		try {
			System.out.print("������ ������ ���̵� �Է��ϼ���:");
			ID=sc.next();
			System.out.print("��й�ȣ�� �Է��ϼ���:");
			password=sc.next();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, ID);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			MemberDTO vo=new MemberDTO(ID,password);
			for(int i=0;i<memArrayList.size();i++) {
			if(memArrayList.get(i).getID().equals(ID)&&memArrayList.get(i).getPassword().equals(password)) {
			System.out.println(memArrayList.get(i).getName()+"���� ������ �����˴ϴ�!");	
			check=true;
			memArrayList.remove(vo);			
			}
			}	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		if (check==false) {
			System.out.println("ID Ȥ�� ��й�ȣ�� �߸� �Ǿ����ϴ�.");
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
