package Customer;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import Inventory.Inventory;

public class MainCustomer extends JFrame {
	public JFrame mainCustFrame = new JFrame("Lesmana Cafe's");
	public static JDesktopPane dePane;
	
public void visible() {
	
	dePane = new JDesktopPane();
	mainCustFrame.setContentPane(dePane);	
	dePane.add(new MenuCustomer());
	
	mainCustFrame.setVisible(true);
	mainCustFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	mainCustFrame.setSize(1200,900);
	mainCustFrame.setLocationRelativeTo(null);
	mainCustFrame.setResizable(false);
}

public void openNewOrder() {
	viewMyCart.dariAdd = false;
	if (dePane.getAllFrames().length == 0) {
		dePane.add(new AddOrder());
	}else {
		dePane.remove(0);
		dePane.add(new AddOrder());
	}
}

public void openViewCart() {
if (dePane.getAllFrames().length == 0) {
	dePane.add(new viewMyCart());
	}else {
		dePane.remove(0);
		dePane.add(new viewMyCart());
	}

}

public void openViewMyOrder() {
if (dePane.getAllFrames().length == 0) {
	dePane.add(new viewMyOrder());
	}else {
		dePane.remove(0);
		dePane.add(new viewMyOrder());
	}

}

public void openMainMenu() {
if (dePane.getAllFrames().length == 0) {
		dePane.add(new MenuCustomer());
	}else {
		dePane.remove(0);
		dePane.add(new MenuCustomer());
	}
}

}
