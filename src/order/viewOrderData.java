package order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Income.incomeData;
import Staff.viewIncome;
import konekDatabase.konekdatabase;

public class viewOrderData extends konekdatabase{

	public static ArrayList<Object[]> viewOrderHeader = new ArrayList<Object[]>();
	public static ArrayList<Object[]> viewOrderSearch = new ArrayList<Object[]>();
	public static ArrayList<Object[]> viewOrderDetail = new ArrayList<Object[]>();
	
	public viewOrderData() throws SQLException{
	
		viewOrderHeader.clear();	
		viewOrderSearch.clear();
		
		String que = "SELECT * FROM `orderheader` ORDER BY `orderDate` DESC";
		ResultSet input = CMSdatabase.executeQuery(que);
		
		while (input.next()) {
			Object sementara[] = {
			input.getInt(1),input.getString(2),input.getString(3),input.getString(4),input.getString(5),input.getInt(6),input.getString(7),input.getInt(8)		
			};
			
			viewOrderHeader.add(sementara);
		}
	
			try {
				new incomeData();
			} catch (Exception e) {
				// TODO: handle exception
			}

		for (int i = 0; i < viewOrderHeader.size(); i++) {
			
			int idstaffincome = (int) viewOrderHeader.get(i)[7] ;
			
			for (int j = 0; j < incomeData.staffName.size(); j++) {
				
				int idstaff = (int) incomeData.staffName.get(j)[0];
				
				if (idstaff == idstaffincome) {
					Object[] sementara = {
							viewOrderHeader.get(i)[0],viewOrderHeader.get(i)[1],viewOrderHeader.get(i)[2],viewOrderHeader.get(i)[3],viewOrderHeader.get(i)[4],viewOrderHeader.get(i)[5],viewOrderHeader.get(i)[6],viewOrderHeader.get(i)[7]
					};
					sementara[7] = incomeData.staffName.get(j)[1];
					viewOrderHeader.set(i, sementara);
				}
			}
		}
		
	}
	
}
