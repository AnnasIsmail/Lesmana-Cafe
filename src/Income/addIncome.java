package Income;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import konekDatabase.konekdatabase;

public class addIncome extends konekdatabase {

	public addIncome(int orderID ,int totalOrder) throws SQLException{
		int id = 0;
		String date = "";
		int done = 0;
		int total = 0;
		
		LocalDateTime DateNow = LocalDateTime.now();  
		DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		String DateNowFormat = DateNow.format(FormatDate);
		
		String getIdque = "SELECT  * FROM `incomeheader`";
		ResultSet input = CMSdatabase.executeQuery(getIdque);
		
		while (input.next()) {
			id = input.getInt(1);
			date = input.getString(2);
			done =  input.getInt(3);
			total = input.getInt(4);
		}
		
		if (DateNowFormat.equals(date)) {
			total = total + totalOrder;
			done = done + 1;
			String queAdd = 
					"UPDATE `incomeheader` "
					+ "SET`incomeTotal`='"+total+"' ,"
					+ "`orderDone`='"+done+"'"
					+ "WHERE  `incomeID`='"+id+"'"
							;
			
			CMSdatabase.execute(queAdd);
			
			String queAddDetail = 
					"INSERT INTO `incomedetail`(`incomeID`, `orderID`) VALUES "
					+ "('"+id+"',"
					+ "'"+orderID+"')"
							;
			
			CMSdatabase.execute(queAddDetail);
			
		}else {
			String getIdque2 = "SELECT `incomeID` FROM `incomeheader`";
			ResultSet input2 = CMSdatabase.executeQuery(getIdque2);
			
			while (input2.next()) {
				id = input2.getInt(1);
			}
			
			String queAdd = 
					"INSERT INTO `incomeheader`(`incomeID`, `incomeDate`, `orderDone`, `incomeTotal`) VALUES "
							+ "('"+(id+1)+"',"
							+ "'"+DateNowFormat+"',"
							+ "'"+1+"',"
							+ "'"+totalOrder+"')"
							;
			
			CMSdatabase.execute(queAdd);
		
			String queAddDetail = 
					"INSERT INTO `incomedetail`(`incomeID`, `orderID`) VALUES "
					+ "('"+(id+1)+"',"
					+ "'"+orderID+"')"
							;
			
			CMSdatabase.execute(queAddDetail);
			
		}
		
		
		
		
	}

}
