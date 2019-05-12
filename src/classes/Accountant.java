package classes;

import java.util.ArrayList;

//import javafx.scene.shape.CullFace;

public class Accountant 
{
	private ArrayList<Bill> Bills;
	
	public Accountant()
	{
		Bills = new Bill().selectBill();
	}
	
	public Bill prepareBill(int _billId, Customer _customer,  Order _order,

							DeliveryBoy _boy, String _date)
	{
		Bill newBill = new Bill();
		newBill.setBillId(_billId);
		newBill.setCustomer(_customer);
		newBill.setOrder(_order);
		newBill.setDelivaryBoy(_boy);
		newBill.setDate(_date);
		newBill.setTotalPrice(new Bill().calculateTotal(_order));
		
		newBill.insertBill(newBill);
		
		for(int i = 0; i < _order.getProducts().size(); i++)
		{
			_order.getProducts().get(i).updateStock(_order.getProducts().get(i).getName(), -1);
		}
		
		Bills.add(newBill);
		
		return newBill;
	}

	public int getBillId()
	{
		return Bills.size()+1;
	}

	public ArrayList<Bill> getBills()
	{
		return Bills;
	}
}
