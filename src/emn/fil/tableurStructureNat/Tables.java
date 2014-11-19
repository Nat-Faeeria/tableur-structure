package emn.fil.tableurStructureNat;

import java.util.ArrayList;

public class Tables extends ArrayList<Table> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Table getTableByName(String name){
		for (Table t : this)
			if (t.getName() == name)
				return t;
		return null;
	}

}
