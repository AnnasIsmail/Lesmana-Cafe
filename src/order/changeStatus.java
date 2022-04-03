package order;

import java.sql.SQLException;

import konekDatabase.konekdatabase;

public class changeStatus extends konekdatabase{

	public changeStatus(int orderID ,String perintah,int idstaff) throws SQLException{
		
		String que = "UPDATE `orderheader` "
				+ "SET `orderStatus`='"+perintah+"', "
							+ "`staffID` = '"+idstaff+"'"
				+ "WHERE `orderID`='"+orderID+"'";
		CMSdatabase.execute(que);
	}

}
