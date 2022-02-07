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
		
		for(;;) { //ùȭ��
			LoginMenu.loginMenu();
			String ID="";
			String password="";
			String name="";
			String address="";		
			int select1;
			select1=sc.nextInt();
			switch(select1) {
			case 1: // �α���
				if (mDao.login(ID, password) == true) { // �α��� ����
					for (;;) {
						carArrayList=cDao.showCarInfo(); // ArrayList�� dbms �������� ����
						CarDAO.carList=carArrayList;
						CarMenu.menu();
						int select2;
						select2 = sc.nextInt();
						switch (select2) {
						case 1: // �������� �����ȸ
							for(int i=0; i<carArrayList.size(); i++) {
								carArrayList.get(i).carInfo();
							}
							break;
						case 2: // �������� ������ȸ
							cDao.searchCarInfo();
							break;
						case 3: // �������� �߰�
							cDao.addCarInfo();
							break;
						case 4: // �������� ����
							cDao.updateCarInfo();
							break;
						case 5: // �������� ����
							cDao.removeCarInfo();
							break;
						case 6:
							System.out.println("���α׷� ����");
							System.exit(0);
						}
						continue;
					}
				}
				System.out.println("���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
				continue;
			case 2: //ȸ������
				mDao.InsertMemberInfo(ID,password,name,address);
				break;
			case 3: //ȸ������ ����
				mDao.updateMemberInfo(ID, password, address);
				break;
			case 4: //ȸ������
				mDao.deleteMemberInfo(ID,password);
				break;
			case 5: //����
				System.out.println("���α׷� ����");
				System.exit(0);
				break;
			}
		}
	}
}
