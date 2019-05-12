package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

public class Manager
{
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	private String Name;
	private String Mobile;
	private String password;
	private String username;
	
	private WebPage onlineShopping;
	 
	public Manager() 
	{
		onlineShopping = new WebPage();
	}
	
	public Manager(String username, String password) {
		onlineShopping = new WebPage();
		this.username = username;
		this.password = password;
	}
	
	public Manager(String Name,String Mobile,String password,String username)
	{
		this.Name=Name;
		this.Mobile=Mobile;
		this.password=password;
		this.username=username;
		
		onlineShopping = new WebPage();
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
	public Manager(WebPage _OnlineShopping)
	{
		this.onlineShopping = _OnlineShopping;
	}
    
	public void addProduct(Product _product, String _categoryName) 
	{
		onlineShopping.addProduct(_product, _categoryName);
	}
	
	public void addCategory(MenuComponent _category)
	{
		onlineShopping.addCategory(_category);
	}

	public void RemoveProduct(String _productName)
	{
		onlineShopping.RemoveProduct(_productName);
	}
	
	public void RemoveCategory(String _categoryName)
	{
		onlineShopping.RemoveCategory(_categoryName);
	}
	
	public ArrayList<MenuComponent> getCategory()
	{
		return onlineShopping.getCategory();
	}

	public ArrayList<Product> getCategoryProducts(String _categoryName)
	{
		return onlineShopping.getCategoryProducts(_categoryName);
	}

	public ArrayList<Product> getProducts()
	{
		return onlineShopping.getProducts();
	}
	
	public ArrayList<Bill> getBills()
	{
		return onlineShopping.getBills();
	}
	public void updateStock(String _productName, int NewQuantity)
	{
		onlineShopping.updateQuantity(_productName, NewQuantity);
	}
	
	public void addDelivayBoy(DeliveryBoy _boy)
	{
		onlineShopping.addDelivayBoy(_boy);

	}

	public ArrayList<DeliveryBoy> getDelivaryBoys() 
	{
		return onlineShopping.getDelivaryBoys();
	}
	
	public void RemoveDelivaryBoy(String _boyName)
	{
		onlineShopping.RemoveDelivaryBoy(_boyName);
	}
	
	public boolean login(String _userName, String _password)
   	{
		boolean res = checkExist(_userName, _password);
		return res;
   	}
	
	public Manager selectManager(String _ManagerUserName)
   	{
		Manager manager = new Manager();
   		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;
			  
	        String strQuery="SELECT * FROM USERES WHERE USERTYPE = 'MANAGER' AND USERNAME = ?";
             preparedStatement = conn.prepareStatement(strQuery);
             preparedStatement.setObject(1, _ManagerUserName);
             
             ResultSet res = preparedStatement.executeQuery();
             while(res.next())
             {
            	 manager.SetUserName(res.getString("USERNAME"));
            	 manager.SetPassword(res.getString("USERPASSWORD"));
            	 manager.SetMobile(res.getString("MOBILENUMBER"));
            	 manager.SetName(res.getString("FULLNAME"));
             }
             
    	    }
    	    catch (Exception e)
    	    {
    	      System.err.println("Select Manager()'oh! Got an exception!"); 
    	      System.err.println(e.getMessage()); 
    	    }
   		return manager;
   	}
   	
   	public ArrayList<Manager> selectManager()
   	{
   		ArrayList<Manager> allManager = new ArrayList<Manager>();
   		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;
			  
	        String strQuery="SELECT * FROM USERES WHERE USERTYPE = ?";
             preparedStatement = conn.prepareStatement(strQuery);
             preparedStatement.setObject(1, "MANAGER");
             ResultSet res = preparedStatement.executeQuery();
             while(res.next())
             {
            	 Manager manager = new Manager();
            	 
            	 manager.SetUserName(res.getString("USERNAME"));
            	 manager.SetPassword(res.getString("USERPASSWORD"));
            	 manager.SetMobile(res.getString("MOBILENUMBER"));
            	 manager.SetName(res.getString("FULLNAME"));
            	 
            	allManager.add(manager);
             }
             
    	    }
    	    catch (Exception e)
    	    {
    	      System.err.println("D'oh! Got an exception!"); 
    	      System.err.println(e.getMessage()); 
    	    }
   		
   		return allManager;
   	}
   	
   	public boolean checkExist(String _userName, String _password)
   	{
   		ArrayList<Manager> allManager = new Manager().selectManager();
   		for(int i = 0; i < allManager.size(); i++) 
   		{
   			if(allManager.get(i).GetUserName().matches(_userName))
   			{
   				if(allManager.get(i).GetPassword().matches(_password))
   				{
   					return true;
   				}
   			}
   		}
   		
   		return false;
   	}
    
   	public void insertManager(Manager _manager)
   	{
   		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="INSERT INTO USERES VALUES (?, ?, ?, ?, ?)";

          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1,  _manager.GetUserName());
          preparedStatement.setObject(2,  _manager.GetPassword());
          preparedStatement.setObject(3,  _manager.GetName());
          preparedStatement.setObject(4,  _manager.GetMobile());
          preparedStatement.setObject(5, "MANAGER");
          
          preparedStatement.executeQuery();
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
}
