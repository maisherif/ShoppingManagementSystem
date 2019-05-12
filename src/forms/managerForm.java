package forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.DeliveryBoy;
import classes.Manager;
import classes.MenuComponent;
import classes.Product;

public class managerForm {

	public JFrame frmManagerForm;
	DefaultListModel CategoryListItem;
	Manager manager;
	
	
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerForm window = new managerForm();
					window.frmManagerForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public managerForm() {
		initialize();
		manager = new Manager();
	}
	
	public managerForm(Manager _manager) {
		initialize();
		manager = _manager;
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmManagerForm = new JFrame();
		frmManagerForm.setTitle("Manager Form");
		frmManagerForm.setSize(new Dimension(700, 543));
		frmManagerForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		DefaultTableModel CategoryTable = new DefaultTableModel();
		CategoryTable.addColumn("Category Name");
		JTable categoryTable = new JTable();
		
		categoryTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		categoryTable.setBackground(SystemColor.menu);
		
		DefaultTableModel ProductTable = new DefaultTableModel();
		ProductTable.addColumn("Product Name");
		ProductTable.addColumn("Description");
		ProductTable.addColumn("Price");
		ProductTable.addColumn("Quantity");
		JTable table = new JTable();
		table.setBackground(SystemColor.menu);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		DefaultTableModel BoyTable = new DefaultTableModel();
		BoyTable.addColumn("Boy Name");
		BoyTable.addColumn("Mobile Num");
		BoyTable.addColumn("Address");
		BoyTable.addColumn("Age");
		BoyTable.addColumn("Status");
		
		JTable boyTable = new JTable();
		boyTable.setBackground(SystemColor.menu);
		boyTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		
		frmManagerForm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) 
			{
				
				String[] firstrow = {"All Product"};
				CategoryTable.addRow(firstrow);
				
				
				ArrayList<MenuComponent> allCategory = manager.getCategory();
				for(int i = 0; i < allCategory.size(); i++)
				{
					String[] row = {allCategory.get(i).getName()};
					CategoryTable.addRow(row);
				}
				categoryTable.setModel(CategoryTable);
				
				
				ArrayList<Product> allProduct = manager.getProducts();
				for(int i = 0; i < allProduct.size(); i++)
				{
					String[] row = { allProduct.get(i).getName(), allProduct.get(i).getDescription(), String.valueOf(allProduct.get(i).getPrice()),String.valueOf(allProduct.get(i).getQuantity())};
					ProductTable.addRow(row);
				}
				table.setModel(ProductTable);
				
				ArrayList<DeliveryBoy> allBoy = manager.getDelivaryBoys();
				for(int i = 0; i < allBoy.size(); i++)
				{

					String[] row = { allBoy.get(i).getName(), allBoy.get(i).getMobile(), allBoy.get(i).getAddress(), String.valueOf(allBoy.get(i).getAge()), allBoy.get(i).getStatus().ToString()};
					BoyTable.addRow(row);
				}
				boyTable.setModel(BoyTable);
			
			}
		});
		
		categoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ProductTable.setRowCount(0);
				if(categoryTable.getModel().getValueAt(categoryTable.getSelectedRow(),0).toString().matches("All Product"))
				{
					ArrayList<Product> allProduct = manager.getProducts();
					for(int i = 0; i < allProduct.size(); i++)
					{
						String[] row = { allProduct.get(i).getName(), allProduct.get(i).getDescription(), String.valueOf(allProduct.get(i).getPrice()),String.valueOf(allProduct.get(i).getQuantity())};
						ProductTable.addRow(row);
					}
				}
				else
				{
					
					ArrayList<Product> allProduct = manager.getCategoryProducts(categoryTable.getModel().getValueAt(categoryTable.getSelectedRow(),0).toString());
					for(int i = 0; i < allProduct.size(); i++)
					{
						String[] row = { allProduct.get(i).getName(), allProduct.get(i).getDescription(), String.valueOf(allProduct.get(i).getPrice()),String.valueOf(allProduct.get(i).getQuantity())};
						ProductTable.addRow(row);
					}
					table.setModel(ProductTable);
				}
			}
		});
		JButton btnAddProduct = new JButton("Add New Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct window = new addProduct(manager);
				window.frame.setVisible(true);
				frmManagerForm.setVisible(false);
			}
		});
		
		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.RemoveProduct(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
				
				categoryTable.setRowSelectionInterval(0, 0);
				ProductTable.setRowCount(0);
				ArrayList<Product> allProduct = manager.getProducts();
				for(int i = 0; i < allProduct.size(); i++)
				{
					String[] row = { allProduct.get(i).getName(), allProduct.get(i).getDescription(), String.valueOf(allProduct.get(i).getPrice()),String.valueOf(allProduct.get(i).getQuantity())};
					ProductTable.addRow(row);
				}
				table.setModel(ProductTable);
			}
		});
		
		JButton btnAddCategory = new JButton("Add New Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategory window = new addCategory(manager);
				window.frame.setVisible(true);
				frmManagerForm.setVisible(false);
			}
		});
		
		JButton btnDeleteCategory = new JButton("Delete Category");
		btnDeleteCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.RemoveCategory(categoryTable.getModel().getValueAt(categoryTable.getSelectedRow(),0).toString());
				
				CategoryTable.setRowCount(0);
				String[] firstrow = {"All Product"};
				CategoryTable.addRow(firstrow);
				
				ArrayList<MenuComponent> allCategory = manager.getCategory();
				for(int i = 0; i < allCategory.size(); i++)
				{
					String[] row = {allCategory.get(i).getName()};
					CategoryTable.addRow(row);
				}
				categoryTable.setModel(CategoryTable);
				
				
				ProductTable.setRowCount(0);
				ArrayList<Product> allProduct = manager.getProducts();
				for(int i = 0; i < allProduct.size(); i++)
				{
					String[] row = { allProduct.get(i).getName(), allProduct.get(i).getDescription(), String.valueOf(allProduct.get(i).getPrice()),String.valueOf(allProduct.get(i).getQuantity())};
					ProductTable.addRow(row);
				}
				table.setModel(ProductTable);
			}
		});
		
		JButton btnAddBoy = new JButton("Add Delivary Boy");
		btnAddBoy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDelivaryBoy window = new addDelivaryBoy(manager);
				window.frame.setVisible(true);
				frmManagerForm.setVisible(false);
			}
		});
		
		JButton btnDeleteBoy = new JButton("Delete Delivay Boy");
		btnDeleteBoy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.RemoveDelivaryBoy(boyTable.getModel().getValueAt(boyTable.getSelectedRow(),0).toString());
				
				BoyTable.setRowCount(0);
				ArrayList<DeliveryBoy> allBoy = manager.getDelivaryBoys();
				for(int i = 0; i < allBoy.size(); i++)
				{

					String[] row = { allBoy.get(i).getName(), allBoy.get(i).getMobile(), allBoy.get(i).getAddress(), String.valueOf(allBoy.get(i).getAge()), allBoy.get(i).getStatus().ToString()};
					BoyTable.addRow(row);
				}
				boyTable.setModel(BoyTable);
			}
		});
		
		
		
		JLabel lblAllCategory = new JLabel("All Category");
		
		JLabel lblCategoryProduct = new JLabel("Category Product");
		
		
		
		JLabel lblDelivaryBoy = new JLabel("Delivary Boy");
		
		JButton btnGetBills = new JButton("Get Bills");
		btnGetBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BillsForm window = new BillsForm(manager);
				window.frame.setVisible(true);
				frmManagerForm.setVisible(false);
			}
		});
		
		JButton btnUpdateQuantity = new JButton("Update Quantity");
		btnUpdateQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStock window = new updateStock(manager, table.getModel().getValueAt(table.getSelectedRow(),0).toString());
				window.frame.setVisible(true);
				frmManagerForm.setVisible(false);
			}
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frmManagerForm.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(lblAllCategory)
					.addGap(193)
					.addComponent(lblCategoryProduct)
					.addContainerGap(311, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDelivaryBoy)
							.addGap(292)
							.addComponent(btnDeleteBoy)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddBoy, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
						.addComponent(boyTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnAddProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAddCategory, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(categoryTable, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnUpdateQuantity)
										.addComponent(btnDeleteProduct))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteCategory)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnGetBills))
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))))
					.addGap(70))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddCategory))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnUpdateQuantity)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeleteCategory)
								.addComponent(btnGetBills)
								.addComponent(btnDeleteProduct))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAllCategory)
						.addComponent(lblCategoryProduct))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(categoryTable, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDelivaryBoy)
						.addComponent(btnAddBoy)
						.addComponent(btnDeleteBoy))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(boyTable, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnAddProduct, btnDeleteProduct});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAddProduct, btnDeleteProduct, btnAddCategory, btnDeleteCategory, btnAddBoy, btnDeleteBoy, btnGetBills, btnUpdateQuantity});
		frmManagerForm.getContentPane().setLayout(groupLayout);
		
		
	}
}
