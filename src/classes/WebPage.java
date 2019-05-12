package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;

public class WebPage implements Observer 
{
	private ArrayList<Product> allProducts;
	private ArrayList<MenuComponent> allCategory;
	private ArrayList<Bill> allBills;
	private ArrayList<DeliveryBoy> allBoys;
	private Accountant accountant;
	
	public WebPage()
	{
		allProducts = new Product().selectProduct();
		allCategory = new Category().selectCategory();
		allBoys = new DeliveryBoy().selectDelivaryBoy();
		accountant = new Accountant();
		allBills = accountant.getBills();
	}
	
	public void update(Product product)
	{
		for(int i = 0; i < allProducts.size(); i++)
		{
			if(allProducts.get(i) == product)
			{
				allProducts.set(i, product);
				new Product().updateQuantity(product.getName(), product.getQuantity());
			}
				
		}
	}
	
	// Summary
	// Customer Function
	// Summary
	
	public ArrayList<MenuComponent> getCategory()
	{
		return allCategory;
	}

	public ArrayList<Product> getCategoryProducts(String _cartegoryName)
	{
		ArrayList<Product> categoryProducts = new Product().selectProduct(_cartegoryName);
		return categoryProducts;
	}
	
	public ArrayList<Product> getCategoryAvalibleProducts(String _cartegoryName)
	{
		ArrayList<Product> categoryProducts = new Product().selectAvalibleProduct(_cartegoryName);
		return categoryProducts;
	}
	
	public ArrayList<Product> getProducts()
	{
		return allProducts;
	}
	
	public ArrayList<Product> getAvalibleProducts()
	{
		return new Product().selectAvalibleProduct();
	}
	
	public Bill BuyProduct(ArrayList<String> _productsName, Customer _customer, String _date)
	{
		ArrayList<Product> _productBuy = new ArrayList<Product>();
		for(int i = 0; i < _productsName.size(); i ++)
		{
			for(int y = 0; y < allProducts.size(); y++)
			{
				if(_productsName.get(i).matches(allProducts.get(y).getName()))
				{
					_productBuy.add(allProducts.get(y));
				}
			}
		}
		
		Order newOrder = new Order();
		newOrder.SetOrder(_customer.GetName());
		for(int i = 0; i < _productBuy.size(); i++)
			newOrder.add(_productBuy.get(i));
		
		int BillId = accountant.getBillId();
		
		DeliveryBoy boy = new DeliveryBoy();
		for(int i = 0; i < allBoys.size(); i++)
		{
			if(allBoys.get(i).canTakeOrder() == true)
			{
				boy = allBoys.get(i);
			}
		}
		
		Bill customerBill = accountant.prepareBill(BillId, _customer, newOrder, boy, _date);
		boy.NewOrder(customerBill);
		
		
		//allProducts = new Product().selectProduct();
		//allBoys = new DeliveryBoy().selectDelivaryBoy();
		//allBills = new Bill().selectBill();
		//allBills.add(customerBill);
		
		return customerBill;
	}
	
	public void register(Customer _customer)
	{
		new Customer().insertCustomer(_customer);
	}
	
	/// Summary
	/// Manger Function
	// Summary
	
	public void addProduct(Product _product, String _categoryName) 
	{
		allProducts.add(_product);
		MenuComponent category;
		
		for(int i = 0; i < allCategory.size(); i++)
		{
			if(allCategory.get(i).getName().matches(_categoryName))
			{
				category = allCategory.get(i);
				allCategory.remove(i);
				category.add(_product);
				allCategory.add(category);
			}
		}
		new Product().insertProduct(_product, _categoryName);
	}
	
	public void addCategory(MenuComponent _category)
	{
		allCategory.add(_category);
		new Category().insertCategory(_category.getName());
		
		for(int i = 0; i < _category.getProducts().size(); i++) {
			addProduct((Product)_category.getProducts().get(i), _category.getName());
			
			new Product().insertProduct(_category.getProducts().get(i), _category.getName());
		}
			
	}

	public void RemoveProduct(String _productName)
	{
		
		for(int i = 0; i < allProducts.size(); i++)
		{
			if(allProducts.get(i).getName().matches(_productName))
			{
				allProducts.remove(allProducts.get(i));
			}
		}
		new Product().deleteProduct(_productName);
	}
	
	public void RemoveCategory(String _categoryName)
	{
		int index = 0;
		boolean exist = false;
		
		for(int i = 0; i < allCategory.size(); i++)
		{
			if( allCategory.get(i).getName().matches(_categoryName))
			{
				exist = true;
				index = i;
			}
		}
		
		if(exist == true)
		{
			for(int i = 0; i <  allCategory.get(index).getProducts().size(); i++)
			{
				RemoveProduct(allCategory.get(index).getProducts().get(i).getName());
			}
			
			allCategory.remove(index);
			new Category().deleteCategory(_categoryName);
		}
		
	}
	
	
	public ArrayList<Bill> getBills()
	{
		return accountant.getBills();
		
	}
	
	public ArrayList<Bill> getCustomerBills(Customer cus)
	{
		ArrayList<Bill> customerBills = new ArrayList<Bill>();
		for(int i = 0; i < allBills.size(); i++) {
			if(allBills.get(i).getCustomer().GetName().equals(cus.GetName())) {
				customerBills.add(allBills.get(i));
			}
		}
		
		return customerBills;
	}
	
	public void updateQuantity(String _productName, int NewQuantity)
	{
		for(int i = 0; i < allProducts.size(); i++)
		{
			if(allProducts.get(i).getName().matches(_productName))
			{
				allProducts.get(i).updateStock(allProducts.get(i).getName(), NewQuantity);
				break;
			}
		}
		
	}
	public void addDelivayBoy(DeliveryBoy _boy)
	{
		allBoys.add(_boy);
		
		new DeliveryBoy().insertDelivaryBoy(_boy);
		
	}
	
	public void RemoveDelivaryBoy(String _boyName)
	{
		for(int i = 0; i < allBoys.size(); i++)
		{
			if(allBoys.get(i).getName().matches(_boyName))
			{
				allBoys.remove(allBoys.get(i));
			}
		}
		
		new DeliveryBoy().deleteDelivaryBoy(_boyName);	
	}
	
	public ArrayList<DeliveryBoy> getDelivaryBoys() 
	{
		return allBoys;
	}
	
	public void register(Manager _manager)
	{
		new Manager().insertManager(_manager);
	}
	
	public boolean returnBill(Bill bill)
	{	
		//if(LocalDate.parse(bill.getDate().toString()).getDayOfMonth() + 14 > LocalDate.now().getDayOfMonth())
		//{
			Order billOrder = bill.getOrder();
			for(int i = 0; i < billOrder.getProducts().size(); i++)
			{
				String productName = billOrder.getProducts().get(i).getName();
				int quantity = billOrder.getProducts().get(i).getQuantity();
				
				billOrder.getProducts().get(i).updateStock(productName, 1);
			}
			
			bill.deleteBill(bill.getBillId());
			return true;
		//}
		//return false;
	}

}