import java.util.LinkedHashMap;

/**
 * Manages the Autoincrement value from the database to the printout version
 */
public class IdManager
{
	private final LinkedHashMap<Integer, Integer> idMap;

	/**
	 * Inits the hashmap
	 */
	public IdManager()
	{
		idMap = new LinkedHashMap<>();
	}

	/**
	 * Adds an entry to the map
	 * @param outId Key
	 * @param dbId Value
	 */
	public void addId(int outId, int dbId)
	{
		idMap.put(outId, dbId);
	}

	/**
	 * Get a value from a key
	 * @param key Key
	 * @return Value
	 */
	public int getIndex(int key)
	{
		return idMap.get(key);
	}

	/**
	 * Clears the map
	 */
	public void clearMap()
	{
		idMap.clear();
	}
}
