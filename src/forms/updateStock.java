package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import classes.Manager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class updateStock {

	public JFrame frame;
	Manager manager;
	String ProductName;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateStock window = new updateStock();
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
	public updateStock(Manager _manager, String _productName) {
		initialize();
		this.manager = _manager;
		this.ProductName = _productName;
	}
	
	public updateStock() {
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
		frame.setBounds(100, 100, 318, 183);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEnterValueTo = new JLabel("Enter Value To Update Stock");
		
		JLabel lblQuantity = new JLabel("Quantity");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.updateStock(ProductName, Integer.valueOf(textField.getText()));
				windowsClose();
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
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterValueTo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuantity)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(103, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblEnterValueTo)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantity)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSave, btnCancel});
		frame.getContentPane().setLayout(groupLayout);
	}
}
