@SuppressWarnings("unused")
public class TableData
{
	private String date, weight, timeDay, exerciseName, exerciseReps;

	public TableData(String date, String weight, String timeDay, String exerciseName, String exerciseReps)
	{
		this.date = date;
		this.weight = weight;
		this.timeDay = timeDay;
		this.exerciseName = exerciseName;
		this.exerciseReps = exerciseReps;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public String getTimeDay()
	{
		return timeDay;
	}

	public void setTimeDay(String timeDay)
	{
		this.timeDay = timeDay;
	}

	public String getExerciseName()
	{
		return exerciseName;
	}

	public void setExerciseName(String exerciseName)
	{
		this.exerciseName = exerciseName;
	}

	public String getExerciseReps()
	{
		return exerciseReps;
	}

	public void setExerciseReps(String exerciseReps)
	{
		this.exerciseReps = exerciseReps;
	}
}