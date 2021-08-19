package Objects;

import java.text.SimpleDateFormat;
/**
 * Date class used in the project
 */
@SuppressWarnings("unused")
public class TrackerDate
{
	public static String getDate()
	{
		return calcDate();
	}

	/**
	 * Returns a standard date
	 * @return Date String
	 */
	private static String calcDate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		return dateFormat.format(date);
	}

	/**
	 * Finds the current hour of the day
	 * @return Hour
	 */
	private static int calcHour()
	{
		SimpleDateFormat hourformat = new SimpleDateFormat("HH");
		java.util.Date date = new java.util.Date();
		return Integer.parseInt(hourformat.format(date));
	}

	/**
	 * Calculates the period of the based on the Objects.TimeOfDay enums
	 * @return
	 */
	public static TimeOfDay getPeriodOfDay()
	{
		int hour = calcHour();
		TimeOfDay timeOfDay;
		if(hour>=0 && hour<12)
			timeOfDay = TimeOfDay.MORNING;
		else if(hour>=12 && hour<=16)
			timeOfDay =  TimeOfDay.AFTERNOON;
		else
			timeOfDay  = TimeOfDay.EVENING;
		return timeOfDay;
	}
}
