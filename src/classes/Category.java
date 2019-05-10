package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Category implements MenuComponent 
{
	private String name;
	private List<MenuComponent> products = new ArrayList<>();
	
	/// Connection Information
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public void add(MenuComponent menuComponent) {
		this.products.add(menuComponent);
	}
	
	public void remove(MenuComponent menuComponent) {
		this.products.remove(menuComponent);
	}
	
	public void setProducts(List<MenuComponent> _products)
	{
		this.products = _products;
	}
	
	@Override
	public MenuComponent getChild(int i) {
		return this.products.get(i);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public double getPrice() {
		return -1;
	}

	@Override
	public int getQuantity() {
		return -1;
	}
	
	@Override
	public void insertProduct(MenuComponent _product, String _category) {
		// TODO Insert Product
	}
	
	@Override
	public void deleteProduct(String _productName) {
		// TODO Delete Product
	}
	
	@Override
	public List<MenuComponent> getProducts() {
		return this.products;
	}
	
	
	public void insertCategory(String _gategoryName)
	{
		try
		{	
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
	      Statement stmt = conn.createStatement();  
          stmt.executeUpdate("INSERT INTO PCATEGORY " + "VALUES ('" + _gategoryName + "')");    
	    }
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	public void deleteCategory(String _gategoryName)
	{
		try
		{
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;
	        String strQuery="DELETE PCATEGORY WHERE CATEGORYNAME = ?";
	        preparedStatement = conn.prepareStatement(strQuery);
	        preparedStatement.setObject(1, _gategoryName);
	        preparedStatement.executeQuery();
	        
	    }
		
	    catch (Exception e)
	    {
	      System.err.println("D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	
	public ArrayList<MenuComponent> selectCategory()
	{
		ArrayList<MenuComponent> allCategory = new ArrayList<MenuComponent>();
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="Select CATEGORYNAME FROM PCATEGORY";

          preparedStatement = conn.prepareStatement(strQuery);
          
          ResultSet res = preparedStatement.executeQuery();
          
          while(res.next())
          {
        	  Category c = new Category(res.getString("CATEGORYNAME"));
        	 
        	  //c.setProducts(new Product().selectProduct(res.getString("CATEGORYNAME")));
        	  allCategory.add(c);
          }
          conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("select category() D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    }
		
		return allCategory;
	}
}
