package Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import konekDatabase.konekdatabase;

public class Inventory extends konekdatabase{
	
	public static ArrayList<Object[]> AllData = new ArrayList<Object[]>();
	
	public static ArrayList<Object[]> Coffe = new ArrayList<Object[]>();
	public static ArrayList<Object[]> NonCoffe = new ArrayList<Object[]>();
	public static ArrayList<Object[]> Snack = new ArrayList<Object[]>();
	public static ArrayList<Object[]> HeavyMeal = new ArrayList<Object[]>();
	public static ArrayList<Object[]> Desserts = new ArrayList<Object[]>();
	
	public Inventory() throws SQLException{
		try {
			
			String que = "select * from inventory";
			ResultSet input = CMSdatabase.executeQuery(que);
			
			AllData.clear();
			Coffe.clear();
			NonCoffe.clear();
			Snack.clear();
			HeavyMeal.clear();
			Desserts.clear();
			
			while (input.next()) {
				
				Object[] arraySementara = {
					input.getInt(1)	,input.getInt(2),input.getString(3),input.getInt(4),input.getInt(5),input.getString(6)
				};
				AllData.add(arraySementara);
				
				if (input.getInt(1) == 1) {
					Coffe.add(arraySementara);					
				}else if (input.getInt(1) == 2) {
					NonCoffe.add(arraySementara);
				}else if (input.getInt(1) == 3) {
					Snack.add(arraySementara);
				}else if (input.getInt(1) == 4) {
					HeavyMeal.add(arraySementara);
				}else if (input.getInt(1) == 5) {
					Desserts.add(arraySementara);
				}
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
