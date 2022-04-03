package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Customer.MainCustomer;
import Staff.MainStaff;

public class Login extends JInternalFrame{
	public Color biruTua = new Color(27,54,92);
	public static boolean CodePassBenar = false;
	public static String StaffName = null;
	
	public Login() {
		
		ui();
		setVisible(true);
		setSize(1192,863);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setLocation(-5,-5);
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);



	}

	private void ui() {
		
		JPanel panelUtama =  new JPanel(new BorderLayout());
		panelUtama.setBorder(new EmptyBorder(250,300,250,300));
		panelUtama.setBackground(biruTua);
		add(panelUtama);
		
		JPanel northPanel, centerPanel, southPanel;
		
		JLabel loginLabel, usernameLabel, passwordLabel ;
		JTextField usernameField;
		JPasswordField passwordField;
		
		JButton loginButton;
		
		northPanel = new JPanel();
		northPanel.setBackground(biruTua);
		centerPanel = new JPanel(new GridLayout(2,2, 0, 26));
		centerPanel.setBackground(biruTua);
		southPanel = new JPanel();
		southPanel.setBackground(biruTua);
		
		loginLabel = new JLabel("Lesmana Cafe Staff Login");
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		
		usernameLabel = new JLabel("Staff Code");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 35));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("SansSerif", Font.PLAIN, 25));
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 35));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 25));
		
		loginButton = new JButton("LOGIN");
		loginButton.setFont(new Font("Calibri", Font.BOLD, 30));
		loginButton.setPreferredSize(new Dimension(100, 55));
		loginButton.setBackground(new Color(71,91,120));
		loginButton.setForeground(Color.WHITE);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				try {
					new LoginVerif(username,password);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				if(username.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Please input Staff Code", "Alert", JOptionPane.WARNING_MESSAGE);				
				}else if(password.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "Please input Password", "Alert", JOptionPane.WARNING_MESSAGE);
				}else if(CodePassBenar == false){
					JOptionPane.showMessageDialog(getParent(), "The Staff Code or Password you entered is wrong!", "Alert", JOptionPane.WARNING_MESSAGE);
				}else if(CodePassBenar == true){
					JOptionPane.showMessageDialog(getParent(), "Welcome and Happy Working " + StaffName);
					MainStaff.berhasilLogin = true;
					new MainStaff().openMainMenuLogin();
				}
			}
		});
		
		northPanel.setBorder(new EmptyBorder(20,0,0,0));
		northPanel.add(loginLabel);
		
		centerPanel.setBorder(new EmptyBorder(20,10,45,0));
		
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		
		southPanel.setBorder(new EmptyBorder(0, 100 ,20 ,100));
		southPanel.setLayout(new GridLayout(1, 2, 10 ,10));
		southPanel.add(loginButton);
		
		
		panelUtama.add(northPanel, BorderLayout.NORTH);
		panelUtama.add(centerPanel, BorderLayout.CENTER);
		panelUtama.add(southPanel, BorderLayout.SOUTH);
	}
}
