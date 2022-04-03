package mainCafe;

// untuk login menjadi Role Owner Staff Code : Bossyahrul01 , Password : Bossyahrul01
// untuk login menjadi Role Barista Staff Code : Annas123 , Password : Annas123
// untuk login menjadi Role Cashier Staff Code : Sandrio456 , Password : Sandrio456

import java.sql.SQLException;

import Customer.MainCustomer;
import Inventory.Inventory;
import Staff.MainStaff;

public class MainForStaff extends MainStaff{

public static void main(String[] args) {
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
	new MainStaff().visible();
}
}

