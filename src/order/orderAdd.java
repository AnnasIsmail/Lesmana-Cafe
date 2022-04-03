package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Cart.Cart;
import Customer.viewMyCart;
import Inventory.Inventory;
import konekDatabase.konekdatabase;

public class orderAdd extends konekdatabase{

	public orderAdd(String orderName,String td, String paymentMethod, int orderTotalPurchase,String OrderStatus) throws SQLException{
		
		int orderID = 0;
		
		try {
			LocalDateTime DateNow = LocalDateTime.now();  
			DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			String DateNowFormat = DateNow.format(FormatDate);
			
			String queGetID = "SELECT `orderID` FROM `orderheader` ORDER BY `orderID`";
			ResultSet input = CMSdatabase.executeQuery(queGetID);
			
			while (input.next()) {
				orderID = input.getInt(1);
			}
			
			String queAdd = 
			"INSERT INTO `orderheader`(`orderID`, `orderName`, `orderTAorDI`, `paymentMethod`, `orderDate`, `orderTotalPurchase`, `orderStatus`, `staffID` ) VALUES ("
					+"'"+(orderID+1)+"',"
					+"'"+orderName+"',"
					+"'"+td+"',"
					+"'"+paymentMethod+"',"
					+"'"+DateNowFormat+"',"
					+"'"+orderTotalPurchase+"'"
					+",'"+OrderStatus+"','1'"
							+")";
			CMSdatabase.execute(queAdd);
			
			for (int i = 0; i < Cart.CartDetail.size(); i++) {
				String queAddDetail = 
						"INSERT INTO `orderdetail`(`orderID`, `inventoryID`, `orderQuatity`) VALUES ("
								+"'"+(orderID+1)+"',"
								+"'"+Cart.CartDetail.get(i)[5]+"',"
								+"'"+Cart.CartDetail.get(i)[2]+"'"
										+")";
						CMSdatabase.execute(queAddDetail);
						int stok = 0;
						for (int j = 0; j <Inventory.AllData.size(); j++) {		
							if ((int) Inventory.AllData.get(j)[1] == (int) Cart.CartDetail.get(i)[5]) {								
								stok = (int) Inventory.AllData.get(j)[4] - (int) Cart.CartDetail.get(i)[2];
							}
						}
						
						String queUpdate ="UPDATE `inventory` SET `inventoryStock` = '"+stok+"' WHERE `inventoryID` = '"+Cart.CartDetail.get(i)[5]+"'";
						CMSdatabase.execute(queUpdate);
						
						viewMyCart.validMasuk = true;
						new Inventory();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

}
