package classes;
import java.util.List;

//import com.sun.glass.ui.Application;

//import com.sun.org.apache.xpath.internal.axes.OneStepIterator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";

	private String Name;
	private String Mobile;
	private String password;
	private String username;
	private Product product;
	private Order order;

	private ArrayList<Bill> Bills = new ArrayList<Bill>();
	WebPage onlineShopping;

	public Customer() {
		//onlineShopping = new WebPage();
	}

	public Customer(String username, String password) {
		this.username = username;
		this.password = password;

		onlineShopping = new WebPage();
	}

	public Customer(String Name,String Mobile,String password,String username)
	{
		this.Name=Name;
		this.Mobile=Mobile;
		this.password=password;
		this.username=username;

		onlineShopping = new WebPage();
	}
	public void addBill(Bill _Bill)
	{
		Bills.add(_Bill);
	}

	public void SetName(String Name)
	{
		this.Name=Name;
	}
	public String GetName()
	{
		return Name;
	}
	public void SetMobile(String Mobile)
	{
		this.Mobile=Mobile;
	}
	public String GetMobile()
	{
		return Mobile;
	}
	public void SetPassword(String password)
	{
		this.password=password;
	}
	public String GetPassword()
	{
		return password;
	}
	public void SetUserName(String username)
	{
		this.username=username;
	}
	public String GetUserName()
	{
		return username;
	}

	public ArrayList<Bill> getBill(Customer cus)
	{
		return onlineShopping.getCustomerBills(cus);
	}

	public void RemoveOrder(Bill _Bill)
	{
		Bills.remove(_Bill);
	}
	public ArrayList<MenuComponent> getCategory()
	{
		return onlineShopping.getCategory();
	}

	public ArrayList<Product> getCategoryProducts(String _categoryName)
	{
		return onlineShopping.getCategoryAvalibleProducts(_categoryName);
	}

	public ArrayList<Product> getProducts()
	{
		return onlineShopping.getAvalibleProducts();
	}

	public Bill BuyProduct(ArrayList<String> _productsName, String _date)
	{
		Bill newBill = onlineShopping.BuyProduct(_productsName, this, _date);
		addBill(newBill);
		return newBill;
	}

	public boolean login(String _userName, String _password)
	{	   	
		boolean res = checkExist(_userName, _password);	   		
		return res;	   			
	}

	public boolean returnBill(Bill bill)
	{
		boolean res = onlineShopping.returnBill(bill);
		return res;
	}

	public Customer selectCustomer(String _customerUserName)
	{
		Customer customer = new Customer();
		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="SELECT * FROM USERES WHERE USERTYPE = 'CUSTOMER' AND USERNAME = ?";
			preparedStatement = conn.prepareStatement(strQuery);
			preparedStatement.setObject(1, _customerUserName);

			ResultSet res = preparedStatement.executeQuery();
			while(res.next())
			{
				customer.SetUserName(res.getString("USERNAME"));
				customer.SetPassword(res.getString("USERPASSWORD"));
				customer.SetMobile(res.getString("MOBILENUMBER"));
				customer.SetName(res.getString("FULLNAME"));
			}
			conn.close();

		}
		catch (Exception e)
		{
			System.err.println("select customer()D'oh! Got an exception!"); 
			System.err.println(e.getMessage()); 
		}
		return customer;
	}

	public ArrayList<Customer> selectCustomer()
	{
		ArrayList<Customer> allCustomer = new ArrayList<Customer>();
		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="SELECT * FROM USERES WHERE USERTYPE = 'CUSTOMER'";
			preparedStatement = conn.prepareStatement(strQuery);

			ResultSet res = preparedStatement.executeQuery();
			while(res.next())
			{
				Customer customer = new Customer();
				customer.SetUserName(res.getString("USERNAME"));
				customer.SetPassword(res.getString("USERPASSWORD"));
				customer.SetMobile(res.getString("MOBILENUMBER"));
				customer.SetName(res.getString("FULLNAME"));
				allCustomer.add(customer);
			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("D'oh! Got an exception!"); 
			System.err.println(e.getMessage()); 
		}

		return allCustomer;
	}

	public boolean checkExist(String _userName, String _password)	      	 
	{
		ArrayList<Customer> allCustomer = new Customer().selectCustomer();	   		


		for(int i = 0; i < allCustomer.size(); i++) {	   		
			if(allCustomer.get(i).GetUserName().matches(_userName)) {	   			
				if(allCustomer.get(i).GetPassword().matches(_password)) {	   				
					return true;	   					
				}	   				
			}	   				   	
		}
		return false;
	}

	public void insertCustomer(Customer _customer)
	{
		try
		{
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="INSERT INTO USERES VALUES (?, ?, ?, ?, ?)";

			preparedStatement = conn.prepareStatement(strQuery);
			preparedStatement.setObject(1, _customer.GetUserName());
			preparedStatement.setObject(2, _customer.GetPassword());
			preparedStatement.setObject(3, _customer.GetName());
			preparedStatement.setObject(4, _customer.GetMobile());
			preparedStatement.setObject(5, "CUSTOMER");

			preparedStatement.executeQuery();
		}
		catch (Exception e)
		{
			System.err.println("D'oh! Got an exception!"); 
			System.err.println(e.getMessage());
		} 
	}

	public void deleteCustomer(Customer _customer)
	{
		try
		{
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="DELETE FROM USERES WHERE USERNAME = ? AND USERTYPE = ?";

			preparedStatement = conn.prepareStatement(strQuery);
			preparedStatement.setObject(1, _customer.GetUserName());
			preparedStatement.setObject(2, "CUSTOMER");

			preparedStatement.executeQuery();
		}
		catch (Exception e)
		{
			System.err.println("D'oh! Got an exception!"); 
			System.err.println(e.getMessage());
		} 
	}

}
