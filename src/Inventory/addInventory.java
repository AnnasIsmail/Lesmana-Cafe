package Inventory;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import konekDatabase.konekdatabase;

public class addInventory extends konekdatabase{

	public addInventory(int getCategoryId, String inventoryName, int inventoryPrice, int inventoryStock, String inventorySource) throws SQLException{
		
		int orderID = 0;
		try {
			String queGetID = "SELECT `inventoryID` FROM `inventory` ORDER BY `inventoryID`";
			ResultSet input = CMSdatabase.executeQuery(queGetID);
			
			while (input.next()) {
				orderID = input.getInt(1);
			}
			
			String queAdd= "INSERT INTO `inventory`(`categoryID`, `inventoryID`, `inventoryName`, `inventoryPrice`, `inventoryStock`, `inventoryImage`) VALUES "
					+"('"+ getCategoryId +"',"
					+"'"+(orderID+1)+"',"
					+"'"+inventoryName+"',"
					+"'"+inventoryPrice+"',"
					+"'"+inventoryStock+"',"
					+"'"+inventorySource.toString()+"')"
					;
			CMSdatabase.execute(queAdd);
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
