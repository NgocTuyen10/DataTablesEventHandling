package corejsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CustomerBean {

	private List<Customer> customers;
	Connection connect = null;
	private int id;
	private String name;
	private String phoneNumber;
	private String address;
	private String city;

	// Customer to update
	private Customer customerUpdate;

	public Customer getCustomerUpdate() {
		return customerUpdate;
	}

	public void setCustomerUpdate(Customer customerUpdate) {
		this.customerUpdate = customerUpdate;
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

	private void initConnectToDB() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/data_tables";
		String username = "root";
		String password = "anhtuyen";
		Class.forName("com.mysql.jdbc.Driver");
		connect = (Connection) DriverManager.getConnection(url, username, password);
	}

	public CustomerBean() throws ClassNotFoundException, SQLException {
		initConnectToDB();
		getListCustomer();
	}

	public void getListCustomer() throws SQLException {
		customers = new ArrayList<Customer>();
		PreparedStatement pstmt = connect.prepareStatement("select * from customer");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("name"));
			customer.setPhoneNumber(rs.getString("phone_number"));
			customer.setAddress(rs.getString("address"));
			customer.setCity(rs.getString("city"));

			customers.add(customer);
		}
		rs.close();
		pstmt.close();
	}

	public List<Customer> getCustomers() throws ClassNotFoundException, SQLException {
		return customers;
	}

	public String deleteRow(Customer customer) throws SQLException {
		String query = "delete from customer where id= ?";
		int id = customer.getId();
		PreparedStatement pstmt = connect.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		getListCustomer();
		return null;
	}

	public String save() throws SQLException {
		UpdateCustomer();
		return "index";
	}

	private void UpdateCustomer() throws SQLException {
		String query = "update customer set name = ?,phone_number = ?, address = ?, city = ? where id = ?";
		String name = customerUpdate.getName();
		String phonenumber = customerUpdate.getPhoneNumber();
		String address = customerUpdate.getAddress();
		String city = customerUpdate.getCity();
		int id = customerUpdate.getId();

		PreparedStatement pstmt = connect.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, phonenumber);
		pstmt.setString(3, address);
		pstmt.setString(4, city);
		pstmt.setInt(5, id);
		pstmt.executeUpdate();
		getListCustomer();

	}

	private void formatData() {
		this.id = 0;
		this.name = "";
		this.phoneNumber = "";
		this.address = "";
		this.city = "";
	}

	public String add() throws SQLException {
		String query = "insert into customer values(?,?,?,?,?)";
		PreparedStatement pstmt = connect.prepareStatement(query);
		pstmt.setInt(1, this.id);
		pstmt.setString(2, this.name);
		pstmt.setString(3, phoneNumber);
		pstmt.setString(4, this.address);
		pstmt.setString(5, this.city);
		pstmt.executeUpdate();
		formatData();
		return null;
	}

	public String edit(Customer customer) {
		// customer.setCanEdit(true);
		// setIdUpdate(customer.getId());
		setCustomerUpdate(customer);
		return "edit";
	}

	public String back() {
		return "back";
	}
}
