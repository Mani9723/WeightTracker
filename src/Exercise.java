@SuppressWarnings("FieldMayBeFinal")
public class Exercise
{
	private String exerciseName;
	private int reps;

	public Exercise(String name, int reps)
	{
		this.exerciseName = name;
		this.reps = reps;
	}

	public String getExerciseName()
	{
		return this.exerciseName;
	}

	public int getReps()
	{
		return this.reps;
	}

}
