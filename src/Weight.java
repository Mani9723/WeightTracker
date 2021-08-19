/**
 * Defines Weight Object
 */
@SuppressWarnings("FieldMayBeFinal")
public class Weight
{
	private double weight;
	private TimeOfDay timeOfDay;

	/**
	 * Initializes the Weight object
	 * @param weight Users weight
	 * @param timeOfDay Period of Dat
	 */
	public Weight(double weight, TimeOfDay timeOfDay)
	{
		this.weight = weight;
		this.timeOfDay = timeOfDay;
	}

	/**
	 * Retrieves the weight
	 * @return Weight
	 */
	public double getWeight()
	{
		return this.weight;
	}

	/**
	 * Retrieves the time of the day
	 * @return TimeOfDay Enum
	 */
	public TimeOfDay getTimeOfDay()
	{
		return this.timeOfDay;
	}
}
