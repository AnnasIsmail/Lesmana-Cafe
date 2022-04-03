package Income;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekDatabase.konekdatabase;

public class incomeData extends konekdatabase{

	public static ArrayList<Object[]> incomeHeader = new ArrayList<Object[]>();
	public static ArrayList<Object[]> incomeDetailFromDatabase = new ArrayList<Object[]>();
	public static ArrayList<Object[]> incomeDetail = new ArrayList<Object[]>();
	public static ArrayList<Object[]> staffName = new ArrayList<Object[]>();
	
	public incomeData() throws SQLException{
		incomeHeader.clear();
		incomeDetail.clear();
		incomeDetailFromDatabase.clear();
		
		String que = "SELECT * FROM `incomeheader` ORDER BY `incomeDate` DESC";
		ResultSet input = CMSdatabase.executeQuery(que);
	
		while (input.next()) {
			
			Object[] sementara = {
				input.getInt(1),input.getString(2),input.getInt(3),input.getInt(4)
			};
			incomeHeader.add(sementara);
		}
	
		String que2 = "SELECT * FROM `incomedetail`";
		ResultSet input2 = CMSdatabase.executeQuery(que2);
		
		while (input2.next()) {
			
			Object[] sementara = {
				input2.getInt(1),input2.getInt(2)
			};
		
			incomeDetailFromDatabase.add(sementara);
		}
			
		for (int i = 0; i < incomeDetailFromDatabase.size(); i++) {
			
			String queOrderHeader = "SELECT `orderName`,`staffID`, `orderTotalPurchase` FROM `orderheader` WHERE `orderID` = '"+incomeDetailFromDatabase.get(i)[1]+"'";
			ResultSet input3 = CMSdatabase.executeQuery(queOrderHeader);
			
			while(input3.next()) {
				Object[] sementara = {
						incomeDetailFromDatabase.get(i)[0],incomeDetailFromDatabase.get(i)[1],input3.getString(1),input3.getInt(2),input3.getInt(3)
					};
				
				incomeDetail.add(sementara);
				
			}
		}
		
		String queGetStaffName = "SELECT `staffID`, `staffName` FROM `staff` ";
		ResultSet input4 = CMSdatabase.executeQuery(queGetStaffName);
		
		while (input4.next()) {
			Object[] sementara = {
					input4.getInt(1),input4.getString(2)
			};
			staffName.add(sementara);
		}

		for (int i = 0; i < incomeDetail.size(); i++) {
			
			int idstaffincome = (int) incomeDetail.get(i)[3] ;
			
			for (int j = 0; j < staffName.size(); j++) {
				
				int idstaff = (int)staffName.get(j)[0];
				
				if (idstaff == idstaffincome) {
					Object[] sementara = {
							incomeDetail.get(i)[0],incomeDetail.get(i)[1],incomeDetail.get(i)[2],incomeDetail.get(i)[3],incomeDetail.get(i)[4]
					};
					sementara[3] = staffName.get(j)[1];
					incomeDetail.set(i, sementara);
				}
			}
		}
		
		
	}

}
