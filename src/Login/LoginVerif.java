package Login;

import java.sql.ResultSet;
import java.sql.SQLException;

import Staff.MainStaff;
import konekDatabase.konekdatabase;

public class LoginVerif extends konekdatabase{

	public LoginVerif(String staffCode , String Password) throws SQLException{
	try {
		String que = "SELECT * FROM `staff` WHERE `staffCode`= '"+staffCode+"' AND `staffPasword` = '"+Password.hashCode()+"'";
		ResultSet input = CMSdatabase.executeQuery(que);
		
		while (input.next()) {
			Login.CodePassBenar = true;
			MainStaff.idStaff = input.getInt(1);
			Login.StaffName = input.getString(2);
			MainStaff.role = input.getString(5);
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	}

}
