package Objects;

/**
 * Represents a row in the database
 */
@SuppressWarnings("unused")
public class TableData
{
	private int id;
	private String date, weight, timeDay, exerciseName, exerciseReps;

	/**
	 * Initializes the row
	 * @param id Id
	 * @param date Date
	 * @param weight Objects.Weight
	 * @param timeDay Objects.TimeOfDay
 	 * @param exerciseName Objects.Exercise Name
	 * @param exerciseReps Objects.Exercise Reps
	 */
	public TableData(int id,String date, String weight, String timeDay, String exerciseName, String exerciseReps)
	{
		this.id = id;
		this.date = date;
		this.weight = weight;
		this.timeDay = timeDay;
		this.exerciseName = exerciseName;
		this.exerciseReps = exerciseReps;
	}

	/**
	 * Gets a value
	 * @return Value
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Setter
	 * @param date Value to update
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * Gets a value
	 * @return Value
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * Setter
	 * @param weight Value to update
	 */
	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	/**
	 * Gets a value
	 * @return Value
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Getter
	 * @return Value
	 */
	public String getTimeDay()
	{
		return timeDay;
	}

	/**
	 * Setter
	 * @param timeDay Value to update
	 */
	public void setTimeDay(String timeDay)
	{
		this.timeDay = timeDay;
	}

	/**
	 * Gets a value
	 * @return Value
	 */
	public String getExerciseName()
	{
		return exerciseName;
	}

	/**
	 * Setter
	 * @param exerciseName Value to update
	 */
	public void setExerciseName(String exerciseName)
	{
		this.exerciseName = exerciseName;
	}

	/**
	 * Gets a value
	 * @return Value
	 */
	public String getExerciseReps()
	{
		return exerciseReps;
	}

	/**
	 * Setter
	 * @param exerciseReps Value to update
	 */
	public void setExerciseReps(String exerciseReps)
	{
		this.exerciseReps = exerciseReps;
	}
}
