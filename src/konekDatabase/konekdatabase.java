package konekDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class konekdatabase {

	public static Statement CMSdatabase;
	public static Connection conn;
	
	public konekdatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			conn= DriverManager.getConnection("jdbc:mysql://localhost/finalproject","root","");
			CMSdatabase = conn.createStatement();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}