package Inventory;

import java.sql.SQLException;

import javax.swing.plaf.InputMapUIResource;

import konekDatabase.konekdatabase;

public class updateInventory extends konekdatabase{

	public updateInventory(int categoryID,String inventoryName,int inventoryPrice,int inventoryStock, String inventoryImage,int inventoryID) throws SQLException{
		try {
			String que = "UPDATE `inventory` SET "
					+ "`categoryID`='"+categoryID+"',"
					+ "`inventoryName`='"+inventoryName+"',"
					+ "`inventoryPrice`='"+inventoryPrice+"',"
					+ "`inventoryStock`='"+inventoryStock+"',"
					+ "`inventoryImage`='"+inventoryImage+"' "
					+ "WHERE `inventoryID`='"+inventoryID+"'";
			CMSdatabase.execute(que);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
