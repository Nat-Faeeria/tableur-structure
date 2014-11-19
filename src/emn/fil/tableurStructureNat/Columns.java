package emn.fil.tableurStructureNat;

import java.util.ArrayList;
import java.util.List;


public class Columns extends ArrayList<Column>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Table table;
	
	public Columns(Table table){
		this.table=table;
	}
	
	/**
	 * Permet de récupérer une Column à partir de son nom
	 * @param columnName : le nom de la Column cherchée
	 * @return : la Column cherchée
	 */
	public Column findColumnByName(Object columnName){
		for (Column c : this)
			if (c.getName()==columnName)
				return c;
		return null;
	}
	
	/**
	 * Permet d'ajouter plusieurs Column à partir de leurs noms
	 * @param type : le type des Column à ajouter
	 * @param columnNames : les noms des Column à ajouter
	 */
	public void addColumns(DataTypes type, Object... columnNames){
		for (Object o : columnNames){
			Column c = new Column(type,o);
			c.addObserver(this.table);
			this.add(c);
		}			
	}
	
	/**
	 * Permet d'ajouter plusieurs Column à partir d'une liste de noms
	 * @param type : le type des Column à ajouter
	 * @param columnNames : la liste des noms des Column à ajouter
	 */
	public void addColumns(DataTypes type, List<Object> columnNames){
		for (Object o : columnNames){
			Column c = new Column(type,o);
			c.addObserver(this.table);
			this.add(c);
		}
	}
	
	/**
	 * Pour la persistance.
	 * @return : les noms de toutes les Column.
	 */
	public List<Object> getColumnNames(){
		List<Object> listColumnNames = new ArrayList<Object>();
		for (Column c : this)
			if (this.indexOf(c)!=0)
				listColumnNames.add(c.getName());
		return listColumnNames;
	}
	
	/**
	 * Lol
	 */
	public String toString(){
		String ret = "";
		for (Column c : this)
			ret+=c.toString();
		return ret;
	}
}
