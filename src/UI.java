import Model.Database;
import Model.IdManager;
import Objects.Exercise;
import Objects.TableData;
import Objects.TrackerDate;
import Objects.Weight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * User Interface
 */
public class UI
{
	static Scanner keyboard = new Scanner(System.in);
	static Database database = new Database();
	static IdManager idManager = new IdManager();

	private static int lastRowIndex = 0;
	private static boolean justUpdate = false;

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args) throws IOException, InterruptedException
	{
		clearTerminal();
		if(database.isDBConnected()) {
			System.out.println("\t\t****Database Connected****");
			Thread.sleep(1000);
			clearTerminal();
			updateIdList(database.getTable());
			while (true) {
				showMainMenu();
				handleInputLogic(getUserChoice());
				System.out.println();
				drawLine();
			}
		}else{
			System.out.println("Database is not connected");
		}
	}

	/**
	 * Clears the Terminal, only set for windows.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void clearTerminal() throws IOException, InterruptedException
	{
		if(System.getProperty("os.name").contains("Windows") ) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
	}

	/**
	 * Branches the user path based on the input
	 * @param choice Option user chooses
	 */
	private static void handleInputLogic(int choice)
	{
		switch(choice){
			case 1:
				boolean result = weightEntry();
				if(result){
					System.out.println("\n****Entry Added****");
				}else{
					System.out.println("\tEntry Not Added");
				}
				sleep(1000);

				break;
			case 2:
				showTable();
				break;
			case 3:
				justUpdate = false;
				deleteRow();
				break;
			case 4:
				clearTable();
				System.out.println("****Table Cleared****");
				break;
			case 5:
				database.closeDB();
				System.exit(0);
		}
	}

	/**
	 * Sleep for a second
	 */
	private static void sleep(int millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays the main menu
	 */
	private static void showMainMenu()
	{
		System.out.println("\tMain Menu");
		System.out.print("1: Add Entry");
		System.out.print("\t2: Show Data Table\n");
		System.out.print("3: Delete Row");
		System.out.print("\t4: Clear Table\n");
		System.out.println("5: Exit");
	}

	/**
	 * Gets initial user choice for the main menu
	 * @return
	 */
	private static int getUserChoice()
	{
		int choice = -1;
		while(choice < 1 || choice > 5) {
			try {
				System.out.print("> ");
				choice = Integer.parseInt(keyboard.nextLine());
			} catch (InputMismatchException e) {
				keyboard.next();
			}
		}

		return choice;
	}

	/**
	 * Add an entry menu
	 * @return True on successful entry
	 */
	private static boolean weightEntry()
	{
		Exercise exercise;
		Weight weight;
		double currWeight;

		System.out.println("\tWeight Entry Mode");
		System.out.println("1) Current Date: " + TrackerDate.getDate());
		System.out.print("2) Current Weight: ");
		currWeight = Double.parseDouble(keyboard.nextLine());

		System.out.println("3) Current Period of Day: " + TrackerDate.getPeriodOfDay().name());

		System.out.print("4) Add Exercise (y/n)? ");
		String ans = keyboard.nextLine();
		if(ans.equalsIgnoreCase("y")){
			System.out.print("\t4.1) Exercise Name: ");
			String eName = keyboard.nextLine();
			System.out.print("\t4.2) Reps/miles/Minutes: ");
			int reps = Integer.parseInt(keyboard.nextLine());
			exercise = new Exercise(eName,reps);
		}else{
			exercise = new Exercise("N/A",0);
		}

		weight = new Weight(currWeight, TrackerDate.getPeriodOfDay());
		justUpdate = true;
		boolean good = database.addEntry(weight,exercise);
		idManager.clearMap();
		updateIdList(database.getTable());
		return good;
	}

	/**
	 * Clears the database table
	 */
	private static void clearTable()
	{
		database.clearTableData();
	}

	/**
	 * Deletes a user defined row
	 */
	private static void deleteRow()
	{
		int rowId;
		System.out.print("Enter Row Number: ");
		rowId = Integer.parseInt(keyboard.nextLine());
		if(rowId > lastRowIndex || rowId < 1){
			System.out.println("****Invalid Row ID: " + rowId +"****");
			sleep(500);
		}else {
			try {
				database.deleteRow(idManager.getIndex(rowId));
				System.out.println("****Row " + rowId + " deleted****");
				sleep(1000);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			idManager.clearMap();
			updateIdList(database.getTable());
		}
	}

	/**
	 * Displays the current table for the user
	 */
	static void showTable()
	{
		ArrayList<TableData> dataArrayList = database.getTable();
		int id = 1;
		System.out.format("%30s\n","TABLE");
		System.out.format("%3s%8s%12s%10s%15s%12s\n","Id","Date"
				,"Weight","Time","Exercise","Reps");
		drawLine();
		for(TableData data : dataArrayList){
			System.out.format("%2d %10s%12s%10s%15s%10s",id++,data.getDate(),
					data.getWeight(), data.getTimeDay(),data.getExerciseName(),
					data.getExerciseReps());

			System.out.println();
		}
	}

	/**
	 * Updates the id conversion map
	 * @param dataArrayList Objects.TableData List
	 */
	private static void updateIdList(ArrayList<TableData> dataArrayList)
	{
		int index = 1;
		for(TableData data : dataArrayList){
			idManager.addId(index, data.getId());
			lastRowIndex = Math.max(lastRowIndex,index);
			index++;
		}
	}

	/**
	 * Duh
	 */
	private static void drawLine()
	{
		System.out.println("-----------------------------------------------" +
				"----------------");
	}
}
