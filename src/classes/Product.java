package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
                                                                                                                                                                                                              
public class Product implements MenuComponent {
	
	/// Connection Information
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	private String name;
	private String description;
	private double price;
	private int quantity;
	
	private List<Observer> observers=new ArrayList<Observer>();
	public Product() {
		super();
		observers = new ArrayList<Observer>();
	}

	public Product(String name, String description, double price, int quantity) {
		super();
		observers = new ArrayList<Observer>();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public MenuComponent getChild(int i) {
		return null;
	}

	@Override
	public List<MenuComponent> getProducts() {
		return null;
	}
//	public void removeproduct(Observer observer){
//        observers.remove(observer);		
//     }
//    public void addproduct(Observer observer){
//        observers.add(observer);		
//     }
	
	@Override
	public void remove(MenuComponent menuComponent) { }
	

	@Override
	public void add(MenuComponent menuComponent) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void updateStock(String productName, int Quantity)
	{
		int oldQuantity = this.getQuantity();
		setQuantity(oldQuantity + Quantity);
		updateQuantity(productName, this.getQuantity());
	}
	
	public void attach(Observer newObserver)
	{
		observers.add(newObserver);
	}
	
	public void notifyObservers() {
		
		for(int i = 0; i < observers.size(); i++)
		{
			observers.get(i).update(this);
		}
	}
	
	public void insertProduct(MenuComponent _product, String _category)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="INSERT INTO PRODUCT VALUES (?, ?, ?, ?, ?)";

          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _product.getName());
          preparedStatement.setObject(2, _product.getDescription());
          preparedStatement.setObject(3, _product.getPrice());
          preparedStatement.setObject(4, _product.getQuantity());
          preparedStatement.setObject(5, _category);
	     
          preparedStatement.executeQuery();
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	public void deleteProduct(String _productName) 
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="DELETE FROM BILLPRODUCT WHERE PRODUCTNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _productName);
          preparedStatement.executeQuery();
          
          conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
          preparedStatement = null;
          strQuery= "DELETE FROM PRODUCT WHERE PRODUCTNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _productName);
         
          preparedStatement.executeQuery();
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	
	
	public ArrayList<Product> selectProduct(String _categoryName)
	{
		ArrayList<Product> products = new ArrayList<Product>();
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From PRODUCT WHERE CATEGORYNAME = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _categoryName);
          
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  products.add(new Product(res.getString("PRODUCTNAME"), res.getString("DESCRIPTION"),
        			  res.getInt("PRICE"), res.getInt("QUANTITY")));
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select prodruct(Category Name) D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return products;
	}
	
	public ArrayList<Product> selectAvalibleProduct(String _categoryName)
	{
		ArrayList<Product> products = new ArrayList<Product>();
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From PRODUCT WHERE CATEGORYNAME = ? and QUANTITY > ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _categoryName);
          preparedStatement.setObject(2, 0);
          
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  products.add(new Product(res.getString("PRODUCTNAME"), res.getString("DESCRIPTION"),
        			  res.getInt("PRICE"), res.getInt("QUANTITY")));
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select prodruct(Category Name) D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return products;
	}
	
	
	public ArrayList<Product> selectProduct()
	{
		ArrayList<Product> products = new ArrayList<Product>();
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From PRODUCT";
          preparedStatement = conn.prepareStatement(strQuery);
          
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  products.add(new Product(res.getString("PRODUCTNAME"), res.getString("DESCRIPTION"),
        			  res.getInt("PRICE"), res.getInt("QUANTITY")));
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select product () D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return products;
	}
	
	public ArrayList<Product> selectAvalibleProduct()
	{
		ArrayList<Product> products = new ArrayList<Product>();
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select * From PRODUCT WHERE QUANTITY > ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, 0);
          
          ResultSet res = preparedStatement.executeQuery();
          while(res.next())
          {
        	  products.add(new Product(res.getString("PRODUCTNAME"), res.getString("DESCRIPTION"),
        			  res.getInt("PRICE"), res.getInt("QUANTITY")));
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select Avalibe product () D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return products;
	}
	
	public void updateQuantity(String _productName, int _newQuantity)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="update PRODUCT set QUANTITY = ? WHERE PRODUCTNAME = ?";

          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _newQuantity);
          preparedStatement.setObject(2, _productName);
         
          preparedStatement.executeQuery();
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
}
