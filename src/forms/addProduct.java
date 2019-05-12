package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.Manager;
import classes.MenuComponent;
import classes.Product;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class addProduct {

	public JFrame frame;
	private JTextField txtProductName;
	private JTextField txtDescrption;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	JComboBox combCategory;
	Manager manager;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProduct window = new addProduct();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public addProduct() {
		initialize();
	}
	
	public addProduct(Manager _manager) {
		initialize();
		this.manager = _manager;
	}
	
	public void windowsClose()
	{
		managerForm window = new managerForm(manager);
		window.frmManagerForm.setVisible(true);
		frame.dispose();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 326);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddNewProduct = new JLabel("Select category to add new product");
		
		JLabel lblCategoryName = new JLabel("Category Name");
		
		JLabel lblProductName = new JLabel("Product Name");
		
		JLabel lblDescription = new JLabel("Description");
		
		JLabel lblPrice = new JLabel("Price");
		
		JLabel lblQuantity = new JLabel("Quantity");
		
		JLabel label = new JLabel("");
		
		txtProductName = new JTextField();
		txtProductName.setColumns(10);
		
		txtDescrption = new JTextField();
		txtDescrption.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		
		combCategory = new JComboBox();
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Product product = new Product(txtProductName.getText(), txtDescrption.getText(), Double.valueOf(txtPrice.getText()), Integer.valueOf(txtQuantity.getText()));
				
				ArrayList<Product> allProduct = manager.getProducts();
				boolean exist = false;
				for(int i = 0; i < allProduct.size(); i++)
				{
					if(allProduct.get(i).getName().matches(txtProductName.getText()))
					{
						exist = true;
					}
				}
				
				if(exist == false)
				{
					manager.addProduct(product, combCategory.getSelectedItem().toString());
					windowsClose();
					
					
				}
				else
				{
					label.setText("This Product exist.");
					label.setForeground(Color.red);
				}
				
				
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				ArrayList<MenuComponent> allCategory = manager.getCategory();
				for(int i = 0; i < allCategory.size(); i++)
				{
					combCategory.addItem(allCategory.get(i).getName());
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
		});
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				windowsClose();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblCategoryName)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(combCategory, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblProductName)
											.addComponent(lblDescription)
											.addComponent(lblPrice)
											.addComponent(lblQuantity))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(txtQuantity)
											.addComponent(txtPrice)
											.addComponent(txtDescrption)
											.addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblAddNewProduct)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(222, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblAddNewProduct)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategoryName)
						.addComponent(combCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductName)
						.addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(txtDescrption, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantity)
						.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSave))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSave, btnCancel});
		frame.getContentPane().setLayout(groupLayout);
	}
}
