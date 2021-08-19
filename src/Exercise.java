/**
 * Represents the Exercise Object
 */
@SuppressWarnings("FieldMayBeFinal")
public class Exercise
{
	private String exerciseName;
	private int reps;

	/**
	 * Default constructor
	 * @param name Name of Exercise
	 * @param reps Reps
	 */
	public Exercise(String name, int reps)
	{
		this.exerciseName = name;
		this.reps = reps;
	}

	/**
	 * Returns the name of exercise
	 * @return String
	 */
	public String getExerciseName()
	{
		return this.exerciseName;
	}

	/**
	 * Returns the reps
	 * @return Int
	 */
	public int getReps()
	{
		return this.reps;
	}

}
