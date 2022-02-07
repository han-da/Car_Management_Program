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
	//게터세터
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
