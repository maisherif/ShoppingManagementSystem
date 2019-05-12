package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Bill 
{
	private String ConnectionURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String ConnectionUserName = "hr";
	private String ConnectionPassword = "hr";


	private int BillId;
	private Order order;
	private Customer customer;
	private DeliveryBoy delivaryBoy;
	private String billDate;
	private double totalPrice;


	public void setBillId(int _billId)
	{
		this.BillId = _billId;
	}

	public int getBillId()
	{
		return this.BillId;
	}

	public void setOrder(Order _order)
	{
		this.order = _order;
	}

	public Order getOrder()
	{
		return this.order;
	}

	public void setCustomer(Customer _customer)
	{
		this.customer = _customer;
	}

	public Customer getCustomer()
	{
		return this.customer;
	}

	public void setDelivaryBoy(DeliveryBoy _boy) 
	{
		this.delivaryBoy = _boy;
	}

	public DeliveryBoy getDelivaryBoy()
	{
		return this.delivaryBoy;
	}

	public void setDate(String _date)
	{
		this.billDate = _date;
	}

	public String getDate() 
	{
		return this.billDate;
	}

	public void setTotalPrice(double _totalPrice)
	{
		this.totalPrice = _totalPrice;
	}

	public double getTotalPrice() 
	{
		return this.totalPrice;
	}

	public double calculateTotal(Order order)
	{
		double totalPrice = 0;

		for(int i = 0; i < order.getProducts().size(); i++)
		{
			totalPrice = totalPrice + order.getProducts().get(i).getPrice();
		}

		this.totalPrice = totalPrice;

		return totalPrice;
	}

	public void insertBill(Bill _bill)
	{
		try
		{
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="INSERT INTO BILL VALUES(?, ?, ?, ?, ?)";

			preparedStatement = conn.prepareStatement(strQuery);
			preparedStatement.setObject(1, _bill.getBillId());
			preparedStatement.setObject(2, _bill.getDate());
			preparedStatement.setObject(3, _bill.getCustomer().GetUserName());
			preparedStatement.setObject(4, _bill.getDelivaryBoy().getName());
			preparedStatement.setObject(5, _bill.getTotalPrice());

			preparedStatement.executeQuery();

			for(int i = 0; i < _bill.getOrder().getProducts().size(); i++)
			{
				try {

					preparedStatement = null;

					strQuery="INSERT INTO BILLPRODUCT VALUES(?, ?)";
					preparedStatement = conn.prepareStatement(strQuery);
					preparedStatement.setObject(1, _bill.getBillId());
					preparedStatement.setObject(2, _bill.getOrder().getProducts().get(i).getName());
					preparedStatement.executeQuery();
				}
				catch (Exception e)
				{
					System.err.println("D'oh! Got an exception!"); 
					System.err.println(e.getMessage()); 
				}  
			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("D'oh! Got an exception!"); 
			System.err.println(e.getMessage()); 
		} 
	}
	public void deleteBill(int _billId)
	{
		try
		{
		  Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  PreparedStatement preparedStatement = null;
          String strQuery="DELETE FROM BILLPRODUCT WHERE BILLID = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _billId);
          preparedStatement.executeQuery();
          conn.close();
          
          conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  preparedStatement = null;
          strQuery="DELETE FROM BOYORDER WHERE BILLID = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _billId);
          preparedStatement.executeQuery();
          conn.close();
          
          conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
		  preparedStatement = null;
          strQuery="DELETE FROM BILL WHERE BILLID = ?";
          preparedStatement = conn.prepareStatement(strQuery);
          preparedStatement.setObject(1, _billId);
          preparedStatement.executeQuery();
          conn.close();
          
    
	    }
	    catch (Exception e)
	    {
	      System.err.println("delete Bill D'oh! Got an exception!"); 
	      System.err.println(e.getMessage()); 
	    } 
	}
	public ArrayList<Bill> selectBill()
	{
		ArrayList<Bill> Bills = new ArrayList<Bill>();

		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="SELECT * FROM BILL";

			preparedStatement = conn.prepareStatement(strQuery);
			ResultSet res = preparedStatement.executeQuery();
			while(res.next())
			{
				Bill b = new Bill();
				b.setBillId(res.getInt("BILLID"));
				b.setDate(res.getString("BILLDATE"));
				b.setTotalPrice(res.getDouble("TOTALPRICE"));
				b.setCustomer(new Customer().selectCustomer(res.getString("CUSTOMER")));
				b.setDelivaryBoy(new DeliveryBoy().selectDelivaryBoy(res.getString("DELIVERYBOY")));
				b.setOrder(new Order().selectBillOrder(b.getBillId()));
				Bills.add(b);

			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("select bill() D'oh! Got an exception!"); 
			System.err.println(e.getMessage()); 
		} 
		return Bills;
	}

	public Bill selectBill(int billId)
	{
		Bill b = new Bill();

		try 
		{  
			Connection conn = DriverManager.getConnection(ConnectionURL, ConnectionUserName, ConnectionPassword);
			PreparedStatement preparedStatement = null;

			String strQuery="SELECT * FROM BILL WHERE BILLID = ?";

			preparedStatement = conn.prepareStatement(strQuery);
			preparedStatement.setObject(1, billId);
			ResultSet res = preparedStatement.executeQuery();
			while(res.next())
			{

				b.setBillId(res.getInt("BILLID"));
				b.setDate(res.getString("BILLDATE"));
				b.setTotalPrice(res.getDouble("TOTALPRICE"));
				b.setCustomer(new Customer().selectCustomer(res.getString("CUSTOMER")));
				b.setDelivaryBoy(new DeliveryBoy().selectDelivaryBoy(res.getString("DELIVERYBOY")));
				b.setOrder(new Order().selectBillOrder(b.getBillId()));

			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("select bill(bill id) D'oh! Got an exception!"); 
			System.err.println(e.getMessage()); 
		} 
		return b;
	}
}
