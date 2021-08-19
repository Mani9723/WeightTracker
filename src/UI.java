import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI
{
	static Scanner keyboard = new Scanner(System.in);
	static Database database = new Database();

	@SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args)
	{
		while (true) {
			showMainMenu();
			handleInputLogic(getUserChoice());
			System.out.println();
		}
	}

	private static void handleInputLogic(int choice)
	{
		switch(choice){
			case 1:
				boolean result = weightEntry();
				if(result){
					System.out.println("Entry Added");
				}else{
					System.out.println("Entry Not Added");
				}
				break;
			case 2:
				showTable();
				break;
			case 3:
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

	private static void showMainMenu()
	{
		System.out.println("\t\tMain Menu");
		System.out.println("1) Add Entry");
		System.out.println("2) Show Data Table");
		System.out.println("3) Delete A Row");
		System.out.println("4) Clear Table");
		System.out.println("5) Exit");
	}

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
	static boolean weightEntry()
	{
		Exercise exercise;
		Weight weight;
		double currWeight;

		System.out.println("\t\tWeight Entry Mode");
		System.out.println("1) Current Date: " + TrackerDate.getDate());
		System.out.print("2) Current Weight: ");
		currWeight = Double.parseDouble(keyboard.nextLine());

		System.out.println("3) Current Period of Day: " + TrackerDate.getPeriodOfDay().name());

		System.out.print("4) Add Exercise (y/n)? ");
		String ans = keyboard.nextLine();
		if(ans.equalsIgnoreCase("y")){
			System.out.print("\t4.1) Exercise Name: ");
			String eName = keyboard.nextLine();
			System.out.print("\t4.2) Reps/miles: ");
			int reps = Integer.parseInt(keyboard.nextLine());
			exercise = new Exercise(eName,reps);
		}else{
			exercise = new Exercise("N/A",0);
		}

		weight = new Weight(currWeight, TrackerDate.getPeriodOfDay());
		return database.addEntry(weight,exercise);
	}

	private static void clearTable()
	{
		database.clearTableData();
	}

	private static void deleteRow()
	{
		int rowId;
		System.out.print("Enter Row Number: ");
		rowId = Integer.parseInt(keyboard.nextLine());
		try {
			database.deleteRow(rowId);
			System.out.println("\tRow " + rowId + " deleted.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void showTable()
	{
		ArrayList<TableData> dataArrayList = database.getStatement();
		System.out.println("\t\t\t\t\t\t\t\tTable");
		System.out.format("%8s%12s%10s%15s%12s\n","Date"
				,"Weight","Time","Exercise","Reps");
		System.out.println("-----------------------------------------------" +
				"----------------");
		for(TableData data : dataArrayList){
			System.out.format("%10s%12s%10s%15s%10s", data.getDate(),
					data.getWeight(), data.getTimeDay(),data.getExerciseName(),
					data.getExerciseReps());

			System.out.println();
		}
	}
}
