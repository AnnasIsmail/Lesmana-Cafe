package order;

import java.sql.ResultSet;
import java.sql.SQLException;

import konekDatabase.konekdatabase;

public class viewOrderDataDetail extends konekdatabase{

	public viewOrderDataDetail(int orderID) throws SQLException{
		viewOrderData.viewOrderDetail.clear();

		String que = "SELECT * FROM `orderdetail` WHERE `orderID` = '"+orderID+"'";
		ResultSet input = CMSdatabase.executeQuery(que);
		
		while (input.next()) {
			Object sementara[] = {
				input.getInt(1),input.getInt(2),input.getInt(3)	
			};
			viewOrderData.viewOrderDetail.add(sementara);
		}
	}

}
