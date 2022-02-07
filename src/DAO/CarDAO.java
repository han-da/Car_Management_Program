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
	
	public ArrayList<CarDTO> showCarInfo() { //차량정보를 컨트롤러 arrayList에 넘겨주기
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
	
	public void searchCarInfo() { //차량정보 조건조회
		System.out.println("1.차량 이름 검색, 2.제조사로 검색, 3.색상으로 검색");
		System.out.print("메뉴 선택 : ");
		int select=sc.nextInt();
		String search;
		String SQL;
		boolean check = false;
		switch(select) {
		case 1: //이름으로 검색
			System.out.print("검색할 차량 이름 입력 : ");
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
					System.out.println("차랑명 : " + carName);
					System.out.println("제조사 : " + company);
					System.out.println("색상 : " + color);
					System.out.println("생산일 : " + bulitDate);
					System.out.println("입고일 : " + warehousingDate);
					System.out.println("가격 : " + price);
					System.out.println("사고유무 : " + accident);
					System.out.println("배기량 : " + displacement);
					System.out.println("트랜스미션(오토, 수동) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.print("검색할 제조사 이름 입력 : ");
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
					System.out.println("차랑명 : " + carName);
					System.out.println("제조사 : " + company);
					System.out.println("색상 : " + color);
					System.out.println("생산일 : " + bulitDate);
					System.out.println("입고일 : " + warehousingDate);
					System.out.println("가격 : " + price);
					System.out.println("사고유무 : " + accident);
					System.out.println("배기량 : " + displacement);
					System.out.println("트랜스미션(오토, 수동) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.print("검색할 색상 입력 : ");
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
					System.out.println("차랑명 : " + carName);
					System.out.println("제조사 : " + company);
					System.out.println("색상 : " + color);
					System.out.println("생산일 : " + bulitDate);
					System.out.println("입고일 : " + warehousingDate);
					System.out.println("가격 : " + price);
					System.out.println("사고유무 : " + accident);
					System.out.println("배기량 : " + displacement);
					System.out.println("트랜스미션(오토, 수동) : " + transmission);
					System.out.println("-----------------------------------------------");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	public void addCarInfo() { //차량정보추가
		System.out.println("차량 정보를 추가합니다.");
		System.out.print("차량 이름을 입력하세요 : ");
		String carName = sc.next();
		System.out.print("제조사를 입력하세요 : ");
		String company = sc.next();
		System.out.print("색깔을 입력하세요 : ");
		String color = sc.next();
		System.out.print("생산일을 입력하세요 : ");
		String bulitDate = sc.next();
		System.out.print("입고일을 입력하세요 : ");
		String warehousingDate = sc.next();
		System.out.print("가격을 입력하세요 :");
		String price = sc.next();
		System.out.print("사고유무를 입력하세요 : ");
		String accident = sc.next();
		System.out.print("배기량을 입력하세요 : ");
		String displacement = sc.next();
		System.out.print("수동,오토를 입력하세요 : ");
		String transmission = sc.next();
		String SQL="insert into car_info(carName, company, color, bulitDate, warehousingDate, price, accident, displacement,transmission)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1,carName);//첫번째 물음표에 name집어넣기
			pstmt.setString(2,company);
			pstmt.setString(3,color);
			pstmt.setString(4,bulitDate);
			pstmt.setString(5,warehousingDate);
			pstmt.setString(6,price);
			pstmt.setString(7,accident);
			pstmt.setString(8,displacement);
			pstmt.setString(9,transmission);
			pstmt.executeQuery();
			System.out.println("차량정보가 추가되었습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCarInfo() { //차량정보 수정
		System.out.println("차량의 이름과 색상으로 구분합니다.");
		System.out.print("수정할 자동차 이름 입력 : ");
		String carName =sc.next();
		System.out.print("수정할 자동차 색상 입력 : ");
		String color =sc.next();
		boolean check = false;
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getCarName().equals(carName) && carList.get(i).getColor().equals(color)) {
				carList.get(i).carInfo();
				System.out.println("수정 가능한 정보");
				System.out.println("1.차량이름 2.제조사 3.색상 4.생산일 5.입고일 6.가격 7.사고유무 8.배기량 9.트랜스미션(수동,오토)");
				System.out.print("수정 할 정보 선택(숫자) : ");
				int select = sc.nextInt();
				String update;
				String SQL;
				switch (select) {
				case 1:
					System.out.print("수정될 자동차 이름 : ");
					update = sc.next();
					SQL="update car_info set carName=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setCarName(update);
						System.out.println("차량이름이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.print("수정될 자동차 제조사 : ");
					update = sc.next();
					SQL="update car_info set company=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setCompany(update);
						System.out.println("차량 제조사가 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.print("수정될 자동차 색상 : ");
					update = sc.next();
					SQL="update car_info set color=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setColor(update);
						System.out.println("차량 색상이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					System.out.print("수정될 자동차 생산일 : ");
					update = sc.next();
					SQL="update car_info set BulitDate=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						System.out.println("자동차 생산일이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.print("수정될 자동차 입고일 : ");
					update = sc.next();
					SQL="update car_info set warehousingDate=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						System.out.println("자동차 입고일이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					System.out.print("수정될 자동차 가격 : ");
					update = sc.next();
					SQL="update car_info set price=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setPrice(update);
						System.out.println("차량 가격이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 7:
					System.out.print("수정될 자동차 사고유무 : ");
					update = sc.next();
					SQL="update car_info set Accident=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setAccident(update);
						System.out.println("사고 유무가 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 8:
					System.out.print("수정될 자동차 배기량 : ");
					update = sc.next();
					SQL="update car_info set Displacement=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setDisplacement(update);
						System.out.println("차량 배기량이 수정 되었습니다.");
					}catch(SQLException e) {
						e.printStackTrace();
					}
					break;
				case 9:
					System.out.print("수정될 자동차 트랜스미션(수동,오토) : ");
					update = sc.next();
					SQL="update car_info set Transmission=? where carName=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,update);
						pstmt.setString(2,carName);
						pstmt.setString(3,color);
						pstmt.executeQuery();
						carList.get(i).setTransmission(update);
						System.out.println("트랜스미션이 수정 되었습니다.");
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
			System.out.println("일치하는 차량 정보가 없습니다.");
		}
	}
	
	public void removeCarInfo() { //차량정보 삭제
		System.out.println("차량의 이름과 색상으로 구분합니다.");
		System.out.print("삭제할 자동차 이름 입력 : ");
		String carName =sc.next();
		System.out.print("삭제할 자동차 색상 입력 : ");
		String color =sc.next();
		boolean check=false;
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getCarName().equals(carName) && carList.get(i).getColor().equals(color)) {
				carList.get(i).carInfo();
				System.out.print("이 차량정보를 정말로 삭제 하시겠습니까? (y/n) : ");
				String choice = sc.next();
				if(choice.equals("y") || choice.equals("Y")) {
					String SQL="delete from car_info where carname=? and color=?";
					try {
						pstmt=con.prepareStatement(SQL);
						pstmt.setString(1,carName);
						pstmt.setString(2,color);
						pstmt.executeQuery();
						carList.remove(i);
						System.out.println("차량정보가 삭제 되었습니다.");
						check=true;
						break;
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
				else if(choice.equals("n") || choice.equals("N")) {
					System.out.println("취소합니다.");
					return;
				}
				else {
					System.out.println("잘못된 입력입니다.");
					return;
				}
			}
		}
		if(check==false) {
			System.out.println("일치하는 차량 정보가 없습니다.");
		}
	}
}
