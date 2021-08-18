@SuppressWarnings("FieldMayBeFinal")
public class Weight
{
	private double weight;
	private TimeOfDay timeOfDay;

	public Weight(double weight, TimeOfDay timeOfDay)
	{
		this.weight = weight;
		this.timeOfDay = timeOfDay;
	}

	public double getWeight()
	{
		return this.weight;
	}

	public TimeOfDay getTimeOfDay()
	{
		return this.timeOfDay;
	}
}
