package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	private String order;
	private List<Product> products = new ArrayList<Product>();
	
	void add(Product product)
	{
		products.add(product);
	}
	void SetOrder(String _order)
	{
		this.order = _order;
	}
	String GetOrder()
	{
		return order;
	}
	
	public List<Product> getProducts()
    {
    	return products;
    }
    
    public void setProducts(List<Product> _product)
    {
    	this.products = _product;
    }
    
	/*
	void SetOrdernumber(String ordernumber)
	{
		this.ordernumber=ordernumber;
	}
	String GetOrdernumber()
	{
		return ordernumber;
	}
	
	*/
	
    public boolean CheckProduct(String name)
     {
    	for(int i = 0; i < products.size(); i++)
		{
    		if(products.get(i).getName()==name&&products.get(i).getQuantity()>0)
    		{
    			return true;
    		}
		}
    	 return false;
    	
     }
    
    
    public Order selectBillOrder(int billId)
    {
    	Order order = new Order();
    	try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;
			  
	        String strQuery="SELECT P.ProductName, P.Description, P.Price, P.Quantity, B.BILLID, B.PRODUCTNAME  FROM BILLPRODUCT B, PRODUCT P WHERE B.BILLID = ? AND P.ProductName = B.PRODUCTNAME";
             preparedStatement = conn.prepareStatement(strQuery);
             preparedStatement.setObject(1, billId);
             ResultSet res = preparedStatement.executeQuery();
             while(res.next())
             {
            	
            	Product p = new Product(res.getString("PRODUCTNAME"), res.getString("DESCRIPTION"),
          			  res.getInt("PRICE"), res.getInt("QUANTITY"));
 
            	order.add(p);
            	
             }
             conn.close();
    	  }
    	    catch (Exception e)
    	    {
    	      System.err.println("select bill order D'oh! Got an exception!"); 
    	      System.err.println(e.getMessage()); 
    	    } 
    	return order;
    }
    
    
    
}
