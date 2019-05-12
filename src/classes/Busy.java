package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Busy implements Status
{
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";
	
	
	public Busy()
	{
		
	}
	public String ToString()
	{
		return "Busy";
	}
	
	public void updateStatus(String _boyName)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
		  
          String strQuery="update DELIVERYBOY set STATUS = 'B' WHERE BOYNAME = ?";

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
}
