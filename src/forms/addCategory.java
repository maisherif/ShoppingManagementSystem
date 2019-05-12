package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.sun.xml.internal.ws.api.message.Message;

import classes.Category;
import classes.Manager;
import classes.MenuComponent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class addCategory {

	public JFrame frame;
	private JTextField txtCategory;
	private JTextField txtCategory_1;
	private JLabel label;
	Manager manager;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCategory window = new addCategory();
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
	public addCategory() {
		initialize();
	}
	
	public addCategory(Manager _manager) {
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
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
		});
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 357, 169);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				manager = new Manager();
				
				Category category = new Category(txtCategory_1.getText());
				
				boolean exist = false;
				ArrayList<MenuComponent> allCategory = manager.getCategory();
				for(int i = 0; i < allCategory.size(); i++)
				{
					if(allCategory.get(i).getName().matches(txtCategory_1.getText()))
					{
						exist = true;
						
					}
				}
				
				if(exist == false)
				{
					manager.addCategory(category);
					label.setText("Saved Successfully");
					label.setForeground(Color.green);
					
					windowsClose();
					
					
					
				}
				else {
					manager.addCategory(category);
					label.setText("This Category Exist .");
					label.setForeground(Color.red);
				}
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				windowsClose();
			}
		});
		
		
		
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the category Name");
		JLabel lblCategoryName = new JLabel("Category Name");
		
		txtCategory_1 = new JTextField();
		txtCategory_1.setColumns(10);
		
		label = new JLabel("");
		label.setForeground(new Color(255, 0, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPleaseEnterThe)
					.addContainerGap(176, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCategoryName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtCategory_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(151, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addContainerGap(285, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPleaseEnterThe)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategoryName)
						.addComponent(txtCategory_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnSave, btnCancel});
		frame.getContentPane().setLayout(groupLayout);
	}
}
