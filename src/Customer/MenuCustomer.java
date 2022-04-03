package Customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.portable.InputStream;

public class MenuCustomer extends JInternalFrame{
    
	public Color biruTua = new Color(27,54,92);
	public Color biruMuda = new Color(149,192,253);
	public Color biruSamar = new Color(71,91,120);
	public JPanel centerPanel;
	public JLabel welcom,	addOrderLabelExitLabel,addOrderLabel,ExitLabel,viewOrderLabel,CartLabel;
	public ImageIcon addOrderImageIcon,ExitImageIcon,CartImageIcon,viewOrderImageIcon;
	public JButton addOrderButton,ExitButton,CartButton,viewOrderButton;
	
	public MenuCustomer (){
		
		ui();
		getContentPane().setBackground(biruTua);
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
	}

	private void ui () {
		setLayout(new BorderLayout());
		
		welcom = new JLabel("Welcome To Lesmana Cafe's", JLabel.CENTER);
		welcom.setForeground(Color.WHITE);
		welcom.setFont(new Font("Dialog", Font.BOLD, 44));
		welcom.setBorder(new EmptyBorder(50,0,10,0));
		add(welcom,BorderLayout.PAGE_START);
		
		centerPanel = new JPanel(new GridLayout(0,2,35,35));
		centerPanel.setBackground(biruTua);
		centerPanel.setBorder(new EmptyBorder(45,150,80,150));
		add(centerPanel, BorderLayout.CENTER);
		
		addOrderImageIcon = new ImageIcon(getClass().getResource("/image/addOrder.png")); 
		addOrderLabel = new JLabel("Add Order",JLabel.CENTER);
		addOrderLabel.setForeground(Color.white);
		addOrderLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		addOrderLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		addOrderLabel.setBorder(new EmptyBorder(0,0,20,0));
		addOrderButton = new JButton(addOrderImageIcon);
		addOrderButton.setLayout(new GridLayout());
		addOrderButton.add(addOrderLabel,BorderLayout.PAGE_END);
		addOrderButton.setSize(100,100);
		addOrderButton.setBackground(biruSamar);
		addOrderButton.setBorder(BorderFactory.createEmptyBorder());
		addOrderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new MainCustomer().openNewOrder();
			}
		});
		centerPanel.add(addOrderButton);
		
		CartImageIcon = new ImageIcon(getClass().getResource("/image/cart.png")); 
		CartLabel = new JLabel("View My Cart",JLabel.CENTER);
		CartLabel.setForeground(Color.white);
		CartLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		CartLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		CartLabel.setBorder(new EmptyBorder(0,0,20,0));
		CartButton = new JButton(CartImageIcon);
		CartButton.setLayout(new GridLayout());
		CartButton.add(CartLabel,BorderLayout.PAGE_END);
		CartButton.setSize(100,100);
		CartButton.setBackground(biruSamar);
		CartButton.setBorder(BorderFactory.createEmptyBorder());
		CartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new MainCustomer().openViewCart();
			}
		});
		centerPanel.add(CartButton);
		
		viewOrderImageIcon = new ImageIcon(getClass().getResource("/image/viewOrder.png")); 
		viewOrderLabel = new JLabel("View My Order",JLabel.CENTER);
		viewOrderLabel.setForeground(Color.white);
		viewOrderLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		viewOrderLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		viewOrderLabel.setBorder(new EmptyBorder(0,0,20,0));
		viewOrderButton = new JButton(viewOrderImageIcon);
		viewOrderButton.setLayout(new GridLayout());
		viewOrderButton.add(viewOrderLabel,BorderLayout.PAGE_END);
		viewOrderButton.setSize(100,100);
		viewOrderButton.setBackground(biruSamar);
		viewOrderButton.setBorder(BorderFactory.createEmptyBorder());
		viewOrderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainCustomer().openViewMyOrder();
			}
		});
		centerPanel.add(viewOrderButton);
		
		ExitImageIcon = new ImageIcon(getClass().getResource("/image/exit.png")); 
		ExitLabel = new JLabel("Exit",JLabel.CENTER);
		ExitLabel.setForeground(Color.white);
		ExitLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		ExitLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		ExitLabel.setBorder(new EmptyBorder(0,0,20,0));
		ExitButton = new JButton(ExitImageIcon);
		ExitButton.setLayout(new GridLayout());
		ExitButton.add(ExitLabel,BorderLayout.PAGE_END);
		ExitButton.setSize(100,100);
		ExitButton.setBackground(biruSamar);
		ExitButton.setBorder(BorderFactory.createEmptyBorder());
		ExitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		centerPanel.add(ExitButton);
	}
}
