package forms;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Category;
import classes.Customer;
import classes.MenuComponent;
import classes.Order;
import classes.Product;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cart extends JFrame {

	private JPanel contentPane;
	private Customer customer;
	private Order order;
	private ArrayList<String> cart;
	private JTable cartTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cart(Customer cus, ArrayList<String> cart) {
		this.customer = cus;
		this.cart = cart;
		initialize();
	}

	public Cart() {
		initialize();
	}

	public void initialize() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				DefaultTableModel defaultTableModel = new DefaultTableModel();

				defaultTableModel.addColumn("Name");

				for(String item : cart) {
					defaultTableModel.addRow(new Object[] {
							item
					});
				}
				cartTable.setModel(defaultTableModel);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Payment payy=new Payment(customer, cart);
				payy.setVisible(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(350, Short.MAX_VALUE)
						.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnBuy, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				);

		cartTable = new JTable();
		scrollPane.setColumnHeaderView(cartTable);
		contentPane.setLayout(gl_contentPane);
	}
}
