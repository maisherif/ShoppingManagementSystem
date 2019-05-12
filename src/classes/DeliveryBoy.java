package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import com.sun.javafx.css.converters.StringConverter;

public class DeliveryBoy {
	
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	private String Name;
	private String Address;
	private String Mobile;
	private int Age;
	
	private Status BoyStatus;
	private ArrayList<Order> Orders;
	
	public DeliveryBoy() {}
	public DeliveryBoy(String _name, String _address, String _mobile, int _age)
	{
		this.Name = _name;
		this.Address = _address;
		this.Mobile = _mobile;
		this.Age = _age;
		
		BoyStatus = new Avalible();
		Orders = new ArrayList<Order>();
	}
	
	public DeliveryBoy(String _name, String _address, String _mobile, int _age, String _Status, ArrayList<Order> _Orders)
	{
		this.Name = _name;
		this.Address = _address;
		this.Mobile = _mobile;
		this.Age = _age;
		this.Orders = _Orders;
		if(_Status.matches("A"))
			this.BoyStatus = new Avalible();
		else
			this.BoyStatus = new Busy();
		Orders = _Orders;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getMobile() {
		return Mobile;
	}
	
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public int getAge() {
		return Age;
	}
	
	public void setAge(int age) {
		Age = age;
	}
	
	public void NewOrder(Bill bill)
	{

		Orders.add(bill.getOrder());
		insertBoyOrder(bill);
		
		if(Orders.size() == 2)
		{
			BoyStatus = new Busy();
			BoyStatus.updateStatus(this.Name);
		}	
			
	}
	
	public boolean canTakeOrder()
	{
		if(BoyStatus.ToString().matches("Busy"))
			return false;
		else
		{
			return true;
		}
	}
	
	public Status getStatus()
	{
		return BoyStatus;
	}
	
	public ArrayList<Order> getOrders()
	{
		return Orders;
	}
	public void insertDelivaryBoy(DeliveryBoy _boy)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="INSERT INTO DELIVERYBOY VALUES (?, ?, ?, ?, ?)";

          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _boy.getName());
          preparedStatement.setObject(2, _boy.getMobile());
          preparedStatement.setObject(3, _boy.getAddress());
          preparedStatement.setObject(4, _boy.getAge());
          preparedStatement.setObject(5, "A");
          
          preparedStatement.executeQuery();
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	
	public void deleteDelivaryBoy(String _boyName)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="DELETE DELIVERYBOY WHERE BOYNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _boyName);
          preparedStatement.executeQuery();
          
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	
	public ArrayList<DeliveryBoy> selectDelivaryBoy()
	{
		ArrayList<DeliveryBoy> Boys = new ArrayList<DeliveryBoy>();
		
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From DELIVERYBOY";
          preparedStatement = conn.prepareStatement(strQuery);
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  Boys.add(new DeliveryBoy(res.getString("BoyName"),res.getString("MOBILENUMBER"), res.getString("ADDRESS"),
        			   res.getInt("AGE"), res.getString("STATUS"), selectBoyOrder(res.getString("BoyName"))));
          }
          conn.close();
	    }
		
	    catch (Exception e)
	    {
	      System.err.println("select delivayboyD'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return Boys;
		
	}
	
	
	public DeliveryBoy selectDelivaryBoy(String _boyName)
	{
		DeliveryBoy boy = new DeliveryBoy();
		
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From DELIVERYBOY WHERE BOYNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _boyName);
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  boy = new DeliveryBoy(res.getString("BOYNAME"),res.getString("MOBILENUMBER"), res.getString("ADDRESS"),
        			   res.getInt("AGE"), res.getString("STATUS"), selectBoyOrder(res.getString("BoyName")));
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select boy(boyName) D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return boy;
		
	}
	
	public void insertBoyOrder(Bill _bill)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="INSERT INTO BOYORDER VALUES (?, ?, ?)";

          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _bill.getBillId());
          preparedStatement.setObject(2, _bill.getDelivaryBoy().getName());
          preparedStatement.setObject(3, "W");
          
          preparedStatement.executeQuery();
          
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Insert Boy Order D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}

	public ArrayList<Order> selectBoyOrder(String _boyName)
	{
		ArrayList<Order> boyOrder = new ArrayList<Order>();
		
		
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From BOYORDER WHERE STATUS = ? and BOYNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, "W");
          preparedStatement.setObject(2, _boyName);
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  Order o = new Order().selectBillOrder(res.getInt("BILLID"));
        	  boyOrder.add(o);
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select boy order  D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return boyOrder;
	}
	
}
