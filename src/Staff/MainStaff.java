package Staff;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Customer.MainCustomer;
import Customer.MenuCustomer;
import Customer.viewMyCart;
import Inventory.Inventory;
import Login.Login;

public class MainStaff extends JFrame{
	
	public static int idStaff = 1;
	public static boolean berhasilLogin = false;
	public static String role = null;
	public JFrame mainStaffFrame = new JFrame("Lesmana Cafe's");
	public static JDesktopPane dePane;

	public void visible() {
		dePane = new JDesktopPane();
		mainStaffFrame.setContentPane(dePane);	
		
		dePane.add(new Login());
		
		mainStaffFrame.setVisible(true);
		mainStaffFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainStaffFrame.setSize(1200,900);
		mainStaffFrame.setLocationRelativeTo(null);
		mainStaffFrame.setResizable(false);
	}

	public void openMainMenuLogin() {
		if (!(role == null) && berhasilLogin == true) {
			dePane.remove(0);
			dePane.add(new menuStaff());		
		}else {
			JOptionPane.showMessageDialog(getParent(), "Please input password", "Alert", JOptionPane.WARNING_MESSAGE);
			mainStaffFrame.setVisible(false);
		}
	}
	
	public void closeOrderINIncome() {
		viewIncome.frameTerbuka = false;
		dePane.remove(0);
	
}
	
	public void openOrderINIncome(int id) {
			
			dePane.add(new OrderINIncome(id),0);
		
	}
	
	public void openInventory() {
		
		if (role.equals("Owner") || role.equals("Barista")) {
			
			if (dePane.getAllFrames().length == 0) {
				dePane.add(new viewInventory());
			}else {
				dePane.remove(0);
				dePane.add(new viewInventory());
			}
		}else {
			JOptionPane.showMessageDialog(getParent(), "Maaf Role yang anda miliki tidak diperkenankan untuk membuka halaman ini");
		}
	}
	
	public void openOrder() {
		if (dePane.getAllFrames().length == 0) {
			dePane.add(new viewOrder());
			}else {
				dePane.remove(0);
				dePane.add(new viewOrder());
			}
	}
	
	public void openOrderStaff() {
		if (role.equals("Owner") || role.equals("Cashier")) {
			if (dePane.getAllFrames().length == 0) {
				dePane.add(new viewIncome());
			}else {
				dePane.remove(0);
				dePane.add(new viewIncome());
			}
		}else {
			JOptionPane.showMessageDialog(getParent(), "Maaf Role yang anda miliki tidak diperkenankan untuk membuka halaman ini");
		}
	}
	
	public void openMainMenu() {
		if (dePane.getAllFrames().length == 0) {
			dePane.add(new menuStaff());
		}else {
			dePane.remove(0);
			dePane.add(new menuStaff());
		}
	}
	
	public void openLogin() {
		if (dePane.getAllFrames().length == 0) {
			dePane.add(new Login());
			}else {
				dePane.remove(0);
				dePane.add(new Login());
			}
	}
	

}
