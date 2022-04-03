package Inventory;

import java.sql.SQLException;

import konekDatabase.konekdatabase;

public class deleteInventory extends konekdatabase{

	public deleteInventory(int idInventory) throws SQLException{
		try {
			
			String que = "DELETE FROM `inventory` WHERE `inventoryID`= '" + idInventory+"'";
			CMSdatabase.execute(que);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
