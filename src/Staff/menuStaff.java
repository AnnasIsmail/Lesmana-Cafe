package Staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class menuStaff extends JInternalFrame{

	public Color biruTua = new Color(27,54,92);
	public Color biruMuda = new Color(149,192,253);
	public Color biruSamar = new Color(71,91,120);
	
	public menuStaff() {

		setLayout(new BorderLayout());
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

	private void ui() {
		
		JLabel welcom = new JLabel("Welcome Staff Of Lesmana Cafe's", JLabel.CENTER);
		welcom.setForeground(Color.WHITE);
		welcom.setFont(new Font("Dialog", Font.BOLD, 44));
		welcom.setBorder(new EmptyBorder(50,0,10,0));
		add(welcom,BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel(new GridLayout(0,2,35,35));
		centerPanel.setBackground(biruTua);
		centerPanel.setBorder(new EmptyBorder(45,150,80,150));
		add(centerPanel, BorderLayout.CENTER);
		
		ImageIcon viewInventoryImageIcon = new ImageIcon(getClass().getResource("/image/inventory.png")); 
		JLabel viewInventoryLabel = new JLabel("View Inventory",JLabel.CENTER);
		viewInventoryLabel.setForeground(Color.white);
		viewInventoryLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		viewInventoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		viewInventoryLabel.setBorder(new EmptyBorder(0,0,20,0));
		JButton viewInventoryButton = new JButton(viewInventoryImageIcon);
		viewInventoryButton.setLayout(new GridLayout());
		viewInventoryButton.add(viewInventoryLabel,BorderLayout.PAGE_END);
		viewInventoryButton.setSize(100,100);
		viewInventoryButton.setBackground(biruSamar);
		viewInventoryButton.setBorder(BorderFactory.createEmptyBorder());
		viewInventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainStaff().openInventory();
				
			}
		});
		centerPanel.add(viewInventoryButton);
		
		ImageIcon viewOrderImageIcon = new ImageIcon(getClass().getResource("/image/ViewOrder.png")); 
		JLabel viewOrderLabel = new JLabel("View Order",JLabel.CENTER);
		viewOrderLabel.setForeground(Color.white);
		viewOrderLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		viewOrderLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		viewOrderLabel.setBorder(new EmptyBorder(0,0,20,0));
		JButton viewOrderButton = new JButton(viewOrderImageIcon);
		viewOrderButton.setLayout(new GridLayout());
		viewOrderButton.add(viewOrderLabel,BorderLayout.PAGE_END);
		viewOrderButton.setSize(100,100);
		viewOrderButton.setBackground(biruSamar);
		viewOrderButton.setBorder(BorderFactory.createEmptyBorder());
		viewOrderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainStaff().openOrder();
				
			}
		});
		centerPanel.add(viewOrderButton);
		
		ImageIcon viewIncomeImageIcon = new ImageIcon(getClass().getResource("/image/income.png")); 
		JLabel viewIncomeLabel = new JLabel("View Income",JLabel.CENTER);
		viewIncomeLabel.setForeground(Color.white);
		viewIncomeLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		viewIncomeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		viewIncomeLabel.setBorder(new EmptyBorder(0,0,20,0));
		JButton viewIncomeButton = new JButton(viewIncomeImageIcon);
		viewIncomeButton.setLayout(new GridLayout());
		viewIncomeButton.add(viewIncomeLabel,BorderLayout.PAGE_END);
		viewIncomeButton.setSize(100,100);
		viewIncomeButton.setBackground(biruSamar);
		viewIncomeButton.setBorder(BorderFactory.createEmptyBorder());
		viewIncomeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainStaff().openOrderStaff();
				
			}
		});
		centerPanel.add(viewIncomeButton);
		
		ImageIcon exitImageIcon = new ImageIcon(getClass().getResource("/image/exit.png")); 
		JLabel exitLabel = new JLabel("Exit",JLabel.CENTER);
		exitLabel.setForeground(Color.white);
		exitLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		exitLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		exitLabel.setBorder(new EmptyBorder(0,0,20,0));
		JButton exitButton = new JButton(exitImageIcon);
		exitButton.setLayout(new GridLayout());
		exitButton.add(exitLabel,BorderLayout.PAGE_END);
		exitButton.setSize(100,100);
		exitButton.setBackground(biruSamar);
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainStaff().openLogin();
			}
		});
		centerPanel.add(exitButton);
	}
}
