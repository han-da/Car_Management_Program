package DTO;

public class MemberDTO {
	private String ID;
	private String password;
	private String name;
	private String address;
	
	
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public MemberDTO(String ID, String password, String name, String address) {
		super();
		this.ID = ID;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	
	public MemberDTO(String ID, String password) {
		super();
		this.ID = ID;
		this.password = password;
		
	}
	public MemberDTO(String ID, String password, String address) {
		super();
		this.ID = ID;
		this.password = password;
		this.address = address;
		
	}


	@Override
	public String toString() {
		return "MemberDTO [ID=" + ID + ", password=" + password + ", name=" + name + ", address=" + address + "]";
	}


	public MemberDTO(){
		
	}
	

}
