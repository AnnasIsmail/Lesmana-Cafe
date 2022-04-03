package mainCafe;

import java.sql.SQLException;

import Customer.MainCustomer;
import Inventory.Inventory;
import Staff.MainStaff;

public class MainForCustomer extends MainCustomer{

	public static void main(String[] args){
		 try {
	         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	             if ("Nimbus".equals(info.getName())) {
	                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                 break;
	             }
	         }
	     } catch (ClassNotFoundException e) {
	         java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
	     } catch (InstantiationException e) {
	         java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
	     } catch (IllegalAccessException e) {
	         java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
	     } catch (javax.swing.UnsupportedLookAndFeelException e) {
	         java.util.logging.Logger.getLogger(MainCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
	     }
		
		 try {
			new Inventory();
		} catch (SQLException e) {
			System.out.println(e);
		}
		new MainCustomer().visible();
	}
}
