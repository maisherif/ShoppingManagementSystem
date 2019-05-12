package forms;

import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import classes.Bill;
import classes.Customer;
import classes.Product;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerBills {

	public JFrame frame;
	Customer customer;
	private JTable billsTable;
	private JTable productsTable;
	ArrayList<Bill> allBills;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerBills window = new CustomerBills();
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
	public CustomerBills() {
		initialize();
	}
	
	public CustomerBills(Customer cus) {
		this.customer = cus;
		this.allBills = customer.getBill(customer);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 403);
		
		billsTable = new JTable();
		billsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				java.util.List<Product> products = allBills.get(billsTable.getSelectedRow()).getOrder().getProducts();
				int numOfProducts = allBills.get(billsTable.getSelectedRow()).getOrder().getProducts().size();
				for (int i = 0; i < numOfProducts; i++) {
					DefaultTableModel defaultTableModel = new DefaultTableModel();
					defaultTableModel.addColumn("Name");
					defaultTableModel.addColumn("Description");
					defaultTableModel.addColumn("Price");
					for (Product product : products) {
						defaultTableModel.addRow(new Object[] {
								product.getName(), product.getDescription(), product.getPrice()
						});
					}
					productsTable.setModel(defaultTableModel);
				}
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				DefaultTableModel defaultTableModel = new DefaultTableModel();
				defaultTableModel.addColumn("Bill ID");
				defaultTableModel.addColumn("Date");
				defaultTableModel.addColumn("Total");
				for (Bill bill : allBills) {
					defaultTableModel.addRow(new Object[] {
							bill.getBillId(), bill.getDate(), bill.getTotalPrice()
					});
				}
				billsTable.setModel(defaultTableModel);
			}
		});
		
		productsTable = new JTable();
		
		JLabel lblBills = new JLabel("Bills");
		lblBills.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnReturnBill = new JButton("Return Bill");
		btnReturnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean res = false;
				for(int i = 0; i < allBills.size(); i++)
				{
					if(allBills.get(i).getBillId() == Integer.valueOf(billsTable.getModel().getValueAt(billsTable.getSelectedRow(),0).toString()))
					{
						res = customer.returnBill(allBills.get(i));
						break;
					}
				}
				
				if(res == true)
					JOptionPane.showMessageDialog(null,"Done");
				else
					JOptionPane.showMessageDialog(null,"You Can not return this Bill");
				
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnReturnBill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(billsTable, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(lblBills))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(productsTable, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(lblProducts))
					.addGap(18)
					.addComponent(btnReturnBill, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnReturnBill)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBills)
								.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(productsTable, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
								.addComponent(billsTable, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
