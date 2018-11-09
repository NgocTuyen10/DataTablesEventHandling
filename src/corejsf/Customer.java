package corejsf;

public class Customer {
	private int id;
	private String name;
	private String phoneNumber;
	private String address;
	private String city;
	//private boolean canEdit;
//
//	public boolean isCanEdit() {
//		return canEdit;
//	}
//
//	public void setCanEdit(boolean canEdit) {
//		this.canEdit = canEdit;
//	}

	public Customer() {

	}

	public Customer(int id, String name, String phoneNumber, String address, String city) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
