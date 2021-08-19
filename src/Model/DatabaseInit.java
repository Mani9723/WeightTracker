package Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Initialzes the database
 */
public class DatabaseInit
{
	/**
	 * Creates static connection to the database
	 * @param file Model.Database file name
	 * @return
	 */
	static Connection connector(String file)
	{
		try{
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:"+file);
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
}
