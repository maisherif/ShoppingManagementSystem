package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import classes.DeliveryBoy;
import classes.Manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class addDelivaryBoy {

	public JFrame frame;
	private JTextField txtName;
	private JTextField txtMobile;
	private JTextField txtAddress;
	private JTextField txtAge;
	
	Manager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addDelivaryBoy window = new addDelivaryBoy();
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
	
	public addDelivaryBoy(Manager _manager) {
		initialize();
		this.manager = _manager;
	}
	
	public addDelivaryBoy() {
		initialize();
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddNewDelivary = new JLabel("Add new Delivary boy");
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblMobileNumber = new JLabel("Mobile Number");
		
		JLabel lblAddress = new JLabel("Address");
		
		JLabel lblAge = new JLabel("Age");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		
		JLabel label = new JLabel("");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager = new Manager();
				ArrayList<DeliveryBoy> allDeliveryBoys = manager.getDelivaryBoys();
				boolean exist = false;
				for(int i = 0; i < allDeliveryBoys.size(); i++)
				{
					if(allDeliveryBoys.get(i).getName().matches(txtName.getText()))
					{
						exist = true;
					}
				}
				
				if(exist == false)
				{
					DeliveryBoy boy = new DeliveryBoy(txtName.getText(), txtAddress.getText(), txtMobile.getText(), Integer.valueOf(txtAge.getText()));
					manager.addDelivayBoy(boy);
					
					windowsClose();
				}
				else
				{
					label.setText("DelevaryBoy Exist");
					label.setForeground(Color.red);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowsClose();
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(230, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblName)
								.addGap(48)
								.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblAddNewDelivary)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMobileNumber)
									.addComponent(lblAddress)
									.addComponent(lblAge))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtAge, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
									.addComponent(txtMobile)))))
					.addContainerGap(176, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblAddNewDelivary)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobileNumber)
						.addComponent(txtMobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge)
						.addComponent(txtAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(label)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSave, btnCancel});
		frame.getContentPane().setLayout(groupLayout);
	}

}
