import Model.Database;
import Model.IdManager;
import Objects.Exercise;
import Objects.TableData;
import Objects.TrackerDate;
import Objects.Weight;

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

	private static boolean justUpdate = false;

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args)
	{
		updateIdList(database.getTable());
		while (true) {
			showMainMenu();
			handleInputLogic(getUserChoice());
			System.out.println();
			drawLine();
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
				sleep();

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
				break;
			case 5:
				database.closeDB();
				System.exit(0);
		}
	}

	/**
	 * Sleep for a second
	 */
	private static void sleep()
	{
		try {
			Thread.sleep(1000);
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

		System.out.println("\tObjects.Weight Entry Mode");
		System.out.println("1) Current Date: " + TrackerDate.getDate());
		System.out.print("2) Current Objects.Weight: ");
		currWeight = Double.parseDouble(keyboard.nextLine());

		System.out.println("3) Current Period of Day: " + TrackerDate.getPeriodOfDay().name());

		System.out.print("4) Add Objects.Exercise (y/n)? ");
		String ans = keyboard.nextLine();
		if(ans.equalsIgnoreCase("y")){
			System.out.print("\t4.1) Objects.Exercise Name: ");
			String eName = keyboard.nextLine();
			System.out.print("\t4.2) Reps/miles/Minutes: ");
			int reps = Integer.parseInt(keyboard.nextLine());
			exercise = new Exercise(eName,reps);
		}else{
			exercise = new Exercise("N/A",0);
		}

		weight = new Weight(currWeight, TrackerDate.getPeriodOfDay());
		justUpdate = true;
		idManager.clearMap();
		updateIdList(database.getTable());
		return database.addEntry(weight,exercise);
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
		try {
			database.deleteRow(idManager.getIndex(rowId));
			System.out.println("****Row " + rowId + " deleted****");
			sleep();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idManager.clearMap();
		updateIdList(database.getTable());

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
				,"Objects.Weight","Time","Objects.Exercise","Reps");
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
