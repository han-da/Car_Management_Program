package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import DTO.CarDTO;

public class CarDAO {
	Scanner sc = new Scanner(System.in);
	public static ArrayList<CarDTO> carList;
	private Connection con;
	private Statement st;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public CarDAO() {
		carList=new ArrayList<CarDTO>();
		try {
			String user = "system";
			String pw = "1234";
			String url= "jdbc:oracle:thin:@localhost:1521:orcl";
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
	
	public ArrayList<CarDTO> showCarInfo() { //���������� ��Ʈ�ѷ� arrayList�� �Ѱ��ֱ�
		String SQL="SELECT * FROM car_info";
		try {
			rs=st.executeQuery(SQL);
			while(rs.next()) {
				String carName=rs.getString("carName");
				String company=rs.getString("company");
				String color=rs.getString("color");
				Date bulitDate=rs.getDate("bulitDate");
				Date warehousingDate=rs.getDate("warehousingDate");
				String price=rs.getString("price");
				String accident=rs.getString("accident");
				String displacement=rs.getString("displacement");
				String transmission=rs.getString("transmission");
				
				CarDTO cvo=new CarDTO(carName,company,color,bulitDate,warehousingDate,price,accident,displacement,transmission);
				carList.add(cvo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return carList;
	}
	
	public void searchCarInfo() { //�������� ������ȸ
		System.out.println("1.���� �̸� �˻�, 2.������� �˻�, 3.�������� �˻�");
		System.out.print("�޴� ���� : ");
		int select=sc.nextInt();
		String search;
		String SQL;
		boolean check = false;
		switch(select) {
		case 1: //�̸����� �˻�
			System.out.print("�˻��� ���� �̸� �Է� : ");
			search=sc.next();
			SQL="SELECT * FROM car_info where carname like ?";
			try {
				pstmt=con.prepareStatement(SQL);
				pstmt.setString(1, "%"+search+"%");
				rs=pstmt.executeQuery();
				while(rs.next()) {
					String carName=rs.getString("carName");
					String company=rs.getString("company");
					String color=rs.getString("color");
					Date bulitDate=rs.getDate("bulitDate");
					Date warehousingDate=rs.getDate("warehousingDate");
					String price=rs.getString("price");
					String accident=rs.getString("accident");
					String displacement=rs.getString("displacement");
					String transmission=rs.getString("transmission");
					System.out.println("������ : " + carName);
					System.out.println("������ : " + company);
					System.out.println("���� : " + color);
					System.out.println("������ : " + bulitDate);
					System.out.println("�԰��� : " + warehousingDate);
					System.out.println("���� : " + price);
					System.out.println("������� : " + accident);
					System.out.println("��ⷮ : " + displacement);
					System.out.println("Ʈ�����̼�(����, ����) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.print("�˻��� ������ �̸� �Է� : ");
			search=sc.next();
			SQL="SELECT * FROM car_info where company like ?";
			try {
				pstmt=con.prepareStatement(SQL);
				pstmt.setString(1, "%"+search+"%");
				rs=pstmt.executeQuery();
				while(rs.next()) {
					String carName=rs.getString("carName");
					String company=rs.getString("company");
					String color=rs.getString("color");
					Date bulitDate=rs.getDate("bulitDate");
					Date warehousingDate=rs.getDate("warehousingDate");
					String price=rs.getString("price");
					String accident=rs.getString("accident");
					String displacement=rs.getString("displacement");
					String transmission=rs.getString("transmission");
					System.out.println("������ : " + carName);
					System.out.println("������ : " + company);
					System.out.println("���� : " + color);
					System.out.println("������ : " + bulitDate);
					System.out.println("�԰��� : " + warehousingDate);
					System.out.println("���� : " + price);
					System.out.println("������� : " + accident);
					System.out.println("��ⷮ : " + displacement);
					System.out.println("Ʈ�����̼�(����, ����) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.print("�˻��� ���� �Է� : ");
			search=sc.next();
			SQL="SELECT * FROM car_info where color like ?";
			try {
				pstmt=con.prepareStatement(SQL);
				pstmt.setString(1, "%"+search+"%");
				rs=pstmt.executeQuery();
				while(rs.next()) {
					String carName=rs.getString("carName");
					String company=rs.getString("company");
					String color=rs.getString("color");
					Date bulitDate=rs.getDate("bulitDate");
					Date warehousingDate=rs.getDate("warehousingDate");
					String price=rs.getString("price");
					String accident=rs.getString("accident");
					String displacement=rs.getString("displacement");
					String transmission=rs.getString("transmission");
					System.out.println("������ : " + carName);
					System.out.println("������ : " + company);
					System.out.println("���� : " + color);
					System.out.println("������ : " + bulitDate);
					System.out.println("�԰��� : " + warehousingDate);
					System.out.println("���� : " + price);
					System.out.println("������� : " + accident);
					System.out.println("��ⷮ : " + displacement);
					System.out.println("Ʈ�����̼�(����, ����) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	public void addCarInfo() { //���������߰�
		System.out.println("���� ������ �߰��մϴ�.");
		System.out.print("���� �̸��� �Է��ϼ��� : ");
		String carName = sc.next();
		System.out.print("�����縦 �Է��ϼ��� : ");
		String company = sc.next();
		System.out.print("������ �Է��ϼ��� : ");
		String color = sc.next();
		System.out.print("�������� �Է��ϼ��� : ");
		String bulitDate = sc.next();
		System.out.print("�԰����� �Է��ϼ��� : ");
		String warehousingDate = sc.next();
		System.out.print("������ �Է��ϼ��� :");
		String price = sc.next();
		System.out.print("��������� �Է��ϼ��� : ");
		String accident = sc.next();
		System.out.print("��ⷮ�� �Է��ϼ��� : ");
		String displacement = sc.next();
		System.out.print("����,���並 �Է��ϼ��� : ");
		String transmission = sc.next();
		String SQL="insert into car_info(carName, company, color, bulitDate, warehousingDate, price, accident, displacement,transmission)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1,carName);//ù��° ����ǥ�� name����ֱ�
			pstmt.setString(2,company);
			pstmt.setString(3,color);
			pstmt.setString(4,bulitDate);
			pstmt.setString(5,warehousingDate);
			pstmt.setString(6,price);
			pstmt.setString(7,accident);
			pstmt.setString(8,displacement);
			pstmt.setString(9,transmission);
			pstmt.executeQuery();
			System.out.println("���������� �߰��Ǿ����ϴ�.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCarInfo() { //�������� ����
		System.out.println("������ �̸��� �������� �����մϴ�.");
		System.out.print("������ �ڵ��� �̸� �Է� : ");
		String carName =sc.next();
		System.out.print("������ �ڵ��� ���� �Է� : ");
		String color =sc.next();
		boolean check = false;
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getCarName().equals(carName) && carList.get(i).getColor().equals(color)) {
				carList.get(i).carInfo();
				System.out.println("���� ������ ����");
				System.out.println("1.�����̸� 2.������ 3.���� 4.������ 5.�԰��� 6.���� 7.������� 8.��ⷮ 9.Ʈ�����̼�(����,����)");
				System.out.print("���� �� ���� ����(����) : ");
				int select = sc.nextInt();
				String update;
				String SQL;
				switch (select) {
				case 1:
					System.out.print("������ �ڵ��� �̸� : ");
					update = sc.next();
					SQL="update car_info set carName=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setCarName(update);
						System.out.println("�����̸��� ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.print("������ �ڵ��� ������ : ");
					update = sc.next();
					SQL="update car_info set company=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setCompany(update);
						System.out.println("���� �����簡 ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.print("������ �ڵ��� ���� : ");
					update = sc.next();
					SQL="update car_info set color=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setColor(update);
						System.out.println("���� ������ ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					System.out.print("������ �ڵ��� ������ : ");
					update = sc.next();
					SQL="update car_info set BulitDate=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						System.out.println("�ڵ��� �������� ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.print("������ �ڵ��� �԰��� : ");
					update = sc.next();
					SQL="update car_info set warehousingDate=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						System.out.println("�ڵ��� �԰����� ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					System.out.print("������ �ڵ��� ���� : ");
					update = sc.next();
					SQL="update car_info set price=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setPrice(update);
						System.out.println("���� ������ ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					System.out.print("������ �ڵ��� ������� : ");
					update = sc.next();
					SQL="update car_info set Accident=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setAccident(update);
						System.out.println("��� ������ ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 8:
					System.out.print("������ �ڵ��� ��ⷮ : ");
					update = sc.next();
					SQL="update car_info set Displacement=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setDisplacement(update);
						System.out.println("���� ��ⷮ�� ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 9:
					System.out.print("������ �ڵ��� Ʈ�����̼�(����,����) : ");
					update = sc.next();
					SQL="update car_info set Transmission=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setTransmission(update);
						System.out.println("Ʈ�����̼��� ���� �Ǿ����ϴ�.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				check = true;
				break;
			}
		}
		if (check == false) {
			System.out.println("��ġ�ϴ� ���� ������ �����ϴ�.");
		}
	}
	
	public void removeCarInfo() { //�������� ����
		System.out.println("������ �̸��� �������� �����մϴ�.");
		System.out.print("������ �ڵ��� �̸� �Է� : ");
		String carName =sc.next();
		System.out.print("������ �ڵ��� ���� �Է� : ");
		String color =sc.next();
		boolean check=false;
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getCarName().equals(carName) && carList.get(i).getColor().equals(color)) {
				carList.get(i).carInfo();
				System.out.print("�� ���������� ������ ���� �Ͻðڽ��ϱ�? (y/n) : ");
				String choice = sc.next();
				if(choice.equals("y") || choice.equals("Y")) {
					String SQL="delete from car_info where carname=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,carName);
						pstmt.setString(2,color);
						pstmt.executeQuery();
						carList.remove(i);
						System.out.println("���������� ���� �Ǿ����ϴ�.");
						check=true;
						break;
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
				else if(choice.equals("n") || choice.equals("N")) {
					System.out.println("����մϴ�.");
					return;
				}
				else {
					System.out.println("�߸��� �Է��Դϴ�.");
					return;
				}
			}
		}
		if(check==false) {
			System.out.println("��ġ�ϴ� ���� ������ �����ϴ�.");
		}
	}
}
