import java.sql.*;
import java.util.ArrayList;

/**
 * Handles all of the transactions with the SQLite atabase
 */
@SuppressWarnings("SqlResolve")
public class Database
{
	private static final String SQL_FILE = "weight_tracker.sqlite";
	private static final String TABLE_NAME = "weight_tracker";
	private static Connection connection;

	/**
	 * Default Constructor. Establishes a connection with the database.
	 */
	public Database()
	{
		 connection =  DatabaseInit.connector(SQL_FILE);
		if(connection == null){
			System.out.println("Database not connected");
			System.exit(1);
		}
		try {
			checkIfTableExists();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Method verifies that the database for the application exists.
	 * If it does not exist then it implies that this is a first time use
	 * and creates a new one in the same directory.
	 *
	 * @throws SQLException - SQL Exception
	 */
	private void checkIfTableExists() throws SQLException
	{
		assert connection != null;
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(null, null,
				TABLE_NAME, null);
		if(!resultSet.next()){
			System.out.println("Empty Database Detected...\nCreating a new one...");
			createMainTable();
			System.out.println("Database weight_tracker.sqlite created...");
		}else{
			System.out.println("****Database Connected****");
		}
		resultSet.close();
	}

	/**
	 * Creates the main table with all the different users.
	 * General Table with all the users that are currently registered.
	 * Only contains the summary not the account transactions of any such detail.
	 * @throws SQLException - Exception
	 */
	private void createMainTable() throws SQLException
	{
		String query = "CREATE TABLE IF NOT EXISTS weight_tracker (\n"
				+ "	id INTEGER PRIMARY KEY NOT NULL UNIQUE,\n"
				+ "	date text NOT NULL,\n"
				+ "	weight text NOT NULL,\n"
				+ "	periodOfDay text NOT NULL,\n"
				+ "	exerciseName text,\n"
				+ "	exerciseReps text\n"
				+ ")";
		createPrepStmtExecute(query);
		System.out.println("Table created weight_tracker");

	}

	/**
	 * General method that generates a SQL statement to be used for queries.
	 * @param query - String query
	 * @throws SQLException - Exception
	 */
	private void createPrepStmtExecute(String query) throws SQLException
	{
		PreparedStatement preparedStatement = null;

		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			assert preparedStatement != null;
			preparedStatement.close();
		}
	}

	/**
	 * Clears the table
	 */
	public void clearTableData()
	{
		String query = " DELETE FROM weight_tracker";
		try {
			createPrepStmtExecute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds a user entry to the database
	 * @param weight Weight
	 * @param exercise Exercise
	 * @return True on success
	 */
	public boolean addEntry(Weight weight, Exercise exercise)
	{
		try {
			return addWeightEntry(weight, exercise);
		}catch (SQLException e){
			return false;
		}
	}

	/**
	 * Adds the user entry to the database
	 * @param weight Weight
	 * @param exercise Exercise
	 * @return True on success
	 */
	@SuppressWarnings({"SqlResolve", "finally", "ReturnInsideFinallyBlock"})
	public boolean addWeightEntry(Weight weight, Exercise exercise) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String query = "INSERT into "+TABLE_NAME+"(date,weight,periodOfDay,exerciseName,exerciseReps)" +
				"VALUES(?,?,?,?,?)";

		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,TrackerDate.getDate());
			preparedStatement.setString(2,Double.toString(weight.getWeight()));
			preparedStatement.setString(3,weight.getTimeOfDay().name());
			preparedStatement.setString(4,exercise.getExerciseName());
			preparedStatement.setString(5,Integer.toString(exercise.getReps()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			assert preparedStatement != null;
			preparedStatement.close();
			return true;
		}
	}

	/**
	 * Retrieves the table from the database
	 * @return Arraylist of table rows
	 */
	public ArrayList<TableData> getTable()
	{
		ArrayList<TableData> observableList = new ArrayList<>();
		String query = "SELECT * from weight_tracker";

		try (Connection connection =  DatabaseInit.connector("weight_tracker.sqlite")) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			if (connection != null)
				preparedStatement = connection.prepareStatement(query);
			if (preparedStatement != null)
				resultSet = preparedStatement.executeQuery();
			while (true) {
				assert resultSet != null;
				if (!resultSet.next()) break;
				observableList.add(new TableData(resultSet.getInt("id"),
						resultSet.getString("date"),
						resultSet.getString("weight") + " lbs ",
						resultSet.getString("periodOfDay"),
						resultSet.getString("exerciseName"),
						resultSet.getString("exerciseReps")));
			}
			return observableList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Deletes a row from the table
	 * @param rowID Row ID to delete
	 * @throws SQLException
	 */
	public void deleteRow(int rowID) throws SQLException
	{
		PreparedStatement preparedStatement;
		String query = "DELETE FROM weight_tracker where id = ?";

		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1,rowID);
		preparedStatement.execute();
	}

	/**
	 * Closes the connection to the database
	 */
	public void closeDB()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
