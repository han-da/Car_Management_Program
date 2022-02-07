package DTO;

import java.sql.Date;

public class CarDTO {
	private String carName;
	private String company;
	private String color;
	private Date bulitDate;
	private Date warehousingDate;	
	private String price;	
	private String accident;
	private String displacement;
	private String transmission;
	
	public CarDTO(String carName, String company, String color, Date bulitDate, 
			Date warehousingDate, String price, String accident, String displacement, String transmission) {
		this.carName = carName;
		this.company = company;
		this.color = color;
		this.bulitDate = bulitDate;
		this.warehousingDate = warehousingDate;
		this.price = price;
		this.accident = accident;
		this.displacement = displacement;
		this.transmission = transmission;
		
	}
	public void carInfo() {
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
	//���ͼ���
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getBulitDate() {
		return bulitDate;
	}
	public void setBulitDate(Date bulitDate) {
		this.bulitDate = bulitDate;
	}
	public Date getWarehousingDate() {
		return warehousingDate;
	}
	public void setWarehousingDate(Date warehousingDate) {
		this.warehousingDate = warehousingDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAccident() {
		return accident;
	}
	public void setAccident(String accident) {
		this.accident = accident;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
}
