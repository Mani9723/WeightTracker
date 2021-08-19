import java.util.LinkedHashMap;

public class IdManager
{
	private LinkedHashMap<Integer, Integer> idMap;

	public IdManager()
	{
		idMap = new LinkedHashMap<>();
	}

	public void addId(int outId, int dbId)
	{
		idMap.put(outId, dbId);
	}

	public int getIndex(int key)
	{
		return idMap.get(key);
	}

	public void clearMap()
	{
		idMap.clear();
	}
}
