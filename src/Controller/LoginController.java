package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.CarDAO;
import DAO.MemberDAO;
import DTO.CarDTO;

public class LoginController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		MemberDAO mDao=new MemberDAO();
		CarDAO cDao=new CarDAO();
		ArrayList<CarDTO> carArrayList=new ArrayList<CarDTO>();	
		
		for(;;) { //첫화면
			LoginMenu.loginMenu();
			String ID="";
			String password="";
			String name="";
			String address="";		
			int select1;
			select1=sc.nextInt();
			switch(select1) {
			case 1: // 로그인
				if (mDao.login(ID, password) == true) { // 로그인 성공
					for (;;) {
						carArrayList=cDao.showCarInfo(); // ArrayList에 dbms 차량정보 삽입
						CarDAO.carList=carArrayList;
						CarMenu.menu();
						int select2;
						select2 = sc.nextInt();
						switch (select2) {
						case 1: // 차량정보 모두조회
							for(int i=0; i<carArrayList.size(); i++) {
								carArrayList.get(i).carInfo();
							}
							break;
						case 2: // 차량정보 조건조회
							cDao.searchCarInfo();
							break;
						case 3: // 차량정보 추가
							cDao.addCarInfo();
							break;
						case 4: // 차량정보 수정
							cDao.updateCarInfo();
							break;
						case 5: // 차량정보 삭제
							cDao.removeCarInfo();
							break;
						case 6:
							System.out.println("프로그램 종료");
							System.exit(0);
						}
						continue;
					}
				}
				System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
				continue;
			case 2: //회원가입
				mDao.InsertMemberInfo(ID,password,name,address);
				break;
			case 3: //회원정보 수정
				mDao.updateMemberInfo(ID, password, address);
				break;
			case 4: //회원삭제
				mDao.deleteMemberInfo(ID,password);
				break;
			case 5: //종료
				System.out.println("프로그램 종료");
				System.exit(0);
				break;
			}
		}
	}
}
