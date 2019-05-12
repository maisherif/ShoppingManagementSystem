package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.Bill;
import classes.Manager;
import classes.Product;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ComboBoxModel;

public class BillsForm {

	public JFrame frame;
	
	private JTextField customerName;
	private JTextField customerMobile;
	private JTextField boyName;
	private JTextField boyMobile;
	private JTextField boyAddress;
	private JTextField boyAge;
	JComboBox comboCustomer;
	Manager manager;
	ArrayList<Bill> allBill;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillsForm window = new BillsForm();
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
	public BillsForm(Manager _manager) {
		initialize();
		this.manager = _manager;
	}
	public BillsForm() {
		initialize();
		manager = new Manager();
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
		
		frame.setBounds(100, 100, 626, 499);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBillInformation = new JLabel("BILL Inforomation");
		
		DefaultTableModel BillTable = new DefaultTableModel();
		BillTable.addColumn("Bill Id");
		BillTable.addColumn("Bill Date");
		BillTable.addColumn("Bill Total Price");
		
		JTable billTable = new JTable();
		
		billTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		billTable.setBackground(SystemColor.menu);
		
		DefaultTableModel ProductTable = new DefaultTableModel();
		ProductTable.addColumn("Product Name");
		ProductTable.addColumn("Description");
		ProductTable.addColumn("Price");
		JTable productTable = new JTable();
		productTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		productTable.setBackground(SystemColor.menu);
		
		
		JLabel lblNewLabel = new JLabel("Customer Info");
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		
		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		
		customerName = new JTextField();
		customerName.setColumns(10);
		
		customerMobile = new JTextField();
		customerMobile.setColumns(10);
		
		JLabel lblDelivaryBoyName = new JLabel("Boy Name");
		
		JLabel lblBoyMobile = new JLabel("Boy Mobile");
		
		JLabel lblBoyAddress = new JLabel("Boy Address");
		
		JLabel lblBoyAge = new JLabel("Boy Age");
		
		boyName = new JTextField();
		boyName.setColumns(10);
		
		boyMobile = new JTextField();
		boyMobile.setColumns(10);
		
		boyAddress = new JTextField();
		boyAddress.setColumns(10);
		
		boyAge = new JTextField();
		boyAge.setColumns(10);
		
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		JComboBox comboBox = new JComboBox(model);
		
		DefaultComboBoxModel model1 = new DefaultComboBoxModel();
		JComboBox comboBox_1 = new JComboBox(model1);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				model.addElement("All Customer");
				
				allBill = manager.getBills();
				for(int i = 0; i < allBill.size(); i++)
				{
					String[] row = {String.valueOf(allBill.get(i).getBillId()), allBill.get(i).getDate(), String.valueOf(allBill.get(i).getTotalPrice())};
					
					if(model.getIndexOf(allBill.get(i).getCustomer().GetName()) == -1 )
					{
						model.addElement(allBill.get(i).getCustomer().GetName());
					}
					
					if(model1.getIndexOf(allBill.get(i).getDate()) == -1 )
					{
						model1.addElement(allBill.get(i).getDate());
					}
					
					BillTable.addRow(row);
				}			
				billTable.setModel(BillTable);
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				windowsClose();
			}
		});
		
		
		
		billTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				for(int i = 0; i < allBill.size(); i++)
				{
					if(String.valueOf(allBill.get(i).getBillId()).matches(billTable.getModel().getValueAt(billTable.getSelectedRow(),0).toString())) 
					{
						customerName.setText(allBill.get(i).getCustomer().GetName());
						customerMobile.setText(allBill.get(i).getCustomer().GetMobile());
						
						boyName.setText(allBill.get(i).getDelivaryBoy().getName());
						boyMobile.setText(allBill.get(i).getDelivaryBoy().getMobile());
						boyAddress.setText(allBill.get(i).getDelivaryBoy().getAddress());
						boyAge.setText(String.valueOf(allBill.get(i).getDelivaryBoy().getAge()));
						
						ProductTable.setRowCount(0);
						
						for(int y = 0; y < allBill.get(i).getOrder().getProducts().size(); y++)
						{
							String[] row = {allBill.get(i).getOrder().getProducts().get(y).getName(), allBill.get(i).getOrder().getProducts().get(y).getDescription(), String.valueOf(allBill.get(i).getOrder().getProducts().get(y).getPrice())};
							ProductTable.addRow(row);
						}
						productTable.setModel(ProductTable);
						break;
					}		
				}
			}
		});
		
		
		JLabel lblDelivaryBoyInfo = new JLabel("Delivary Boy Info");
		
		JLabel lblCustomerFilter = new JLabel("Customer Filter");
		
		JLabel lblDateFilter = new JLabel("Date Filter");
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BillTable.setRowCount(0);
				if(comboBox.getSelectedItem().toString().matches("All Customer"))
		           {
		        	   for(int i = 0; i < allBill.size(); i++)
						{
							String[] row = {String.valueOf(allBill.get(i).getBillId()), allBill.get(i).getDate(), String.valueOf(allBill.get(i).getTotalPrice())};
							BillTable.addRow(row);
						}
		        	   billTable.setModel(BillTable);
		           }
		           else
		           {
		        	   for(int i = 0; i < allBill.size(); i++)
						{
		        		   if(allBill.get(i).getCustomer().GetName().matches(comboBox.getSelectedItem().toString()))
		        		   {
							String[] row = {String.valueOf(allBill.get(i).getBillId()), allBill.get(i).getDate(), String.valueOf(allBill.get(i).getTotalPrice())};
							BillTable.addRow(row);
		        		   }
						}
		        	   billTable.setModel(BillTable);
		           }
		        }
			
			
		});
		
		JButton btnFilter_1 = new JButton("Filter");
		btnFilter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillTable.setRowCount(0);
				for(int i = 0; i < allBill.size(); i++)
				{
        		   if(allBill.get(i).getDate().matches(comboBox_1.getSelectedItem().toString()))
        		   {
					String[] row = {String.valueOf(allBill.get(i).getBillId()), allBill.get(i).getDate(), String.valueOf(allBill.get(i).getTotalPrice())};
					BillTable.addRow(row);
        		   }
				}
        	   billTable.setModel(BillTable);
			}
		});
		
		JLabel lblBillProduct = new JLabel("Bill Product");
		
		
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(productTable, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBillInformation)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCustomerFilter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(btnFilter)
							.addGap(31)
							.addComponent(lblDateFilter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFilter_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(billTable, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBoyAge)
								.addComponent(lblBoyAddress)
								.addComponent(lblBoyMobile)
								.addComponent(lblDelivaryBoyName))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(boyAddress, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(boyMobile, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(boyAge, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(boyName, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(236)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(236)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCustomerMobile)
								.addComponent(lblCustomerName))
							.addGap(15)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(customerName, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(customerMobile, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(236)
							.addComponent(lblDelivaryBoyInfo))
						.addComponent(lblBillProduct))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBillInformation)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerFilter)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFilter)
						.addComponent(lblDateFilter)
						.addComponent(btnFilter_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(billTable, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(lblCustomerName))
								.addComponent(customerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCustomerMobile)
								.addComponent(customerMobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addComponent(lblDelivaryBoyInfo)
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDelivaryBoyName)
								.addComponent(boyName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBoyMobile)
								.addComponent(boyMobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBoyAddress)
								.addComponent(boyAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBoyAge)
								.addComponent(boyAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBillProduct)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(productTable, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {customerName, customerMobile});
		frame.getContentPane().setLayout(groupLayout);
	}
}
