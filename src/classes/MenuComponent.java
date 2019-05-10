package classes;

import java.util.ArrayList;
import java.util.List;



public interface MenuComponent {
	public void add(MenuComponent menuComponent);
	public void remove(MenuComponent menuComponent);
	public abstract MenuComponent getChild(int i);
	public abstract List<MenuComponent> getProducts();
	public abstract String getName();
	public abstract String getDescription();
	public abstract double getPrice();
	public abstract int getQuantity();
	public abstract void insertProduct(MenuComponent _product, String _category);
	public abstract void deleteProduct(String _productName);
    
}

