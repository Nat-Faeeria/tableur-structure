package emn.fil.tableurStructureNat;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Column extends Observable{
	
	
	private Object name;
	private ArrayList<Cell> values;
	private DataTypes type;
	
	public Column(DataTypes type, Object name){
		this.name = name;
		this.values=new ArrayList<Cell>();
		this.type=type;
	}
	
	/**
	 * Vérifie si le type d'une valeur correspond à celui de la Column avant de l'insérer dans la Column
	 * @param value
	 */
	private void checktype(Object value){
			if(type.equals(DataTypes.INT)){
				int i = (int) value;
			}else if(type.equals(DataTypes.DOUBLE)){
				double d = (double) value;
			}else if(type.equals(DataTypes.VARCHAR)){
				String s = (String) value;
			}
	}
	
	/**
	 * Crée une Cell contenant la nouvelle valeur et l'ajoute dans la Column
	 * @param value : valeur à ajouter à la Column
	 */
	public void addValue(Object value){
		this.checktype(value);
		this.values.add(new Cell(value));
		this.setChanged();
		notifyObservers(this.size());
	}
	
	/**
	 * Crée une Cell par valeur et ajoute la valeur dans la Cell
	 * @param values : valeurs à ajouter à la Column
	 */
	public void addValues(Object... values){
		for (Object v : values)
			this.addValue(v);
	}
	
	/**
	 * Ajoute une valeur dans la Cell d'index précisé
	 * @param index
	 * @param value
	 */
	public void insert(int index, Object value){
		this.checktype(value);
		Cell c =this.values.get(index);
		c.setValue(value);
	}
	
	public Object getName() {
		return name;
	}	

	public DataTypes getType() {
		return type;
	}

	public ArrayList<Cell> getValues() {
		return values;
	}
	
	/**
	 * @return : le nombre de Cell de la Column
	 */
	public int size(){
		return this.values.size();
	}
	
	/**
	 * Méthode de "persistance" en code Java
	 * @return le code nécessaire, faut pas trop chercher
	 */
	public String saveMeUp(){
		return "new Column(DataTypes."+this.type+",\""+this.name+"\")";
	}
	
	/**
	 * Méthode utilisée par la "persistance"
	 * @return : les valeurs de toutes les Cell de la Column concaténées sous forme de String
	 */
	public String getCellValues(){
		String toReturn = "";
		if (type.equals(DataTypes.VARCHAR)){
			for (Cell c : this.values){
				toReturn+="\""+c.getValue()+"\",";
			}
		}else{
			for (Cell c : this.values){
				toReturn+=c.getValue()+",";
			}
		}
		
		if (toReturn.length()!=0){
			toReturn=toReturn.substring(0, (toReturn.length()-1));
		}
		return toReturn;
	}
	
	/**
	 * Lol ...
	 */
	public String toString(){
		String ret = this.name+"[";
		for (Cell c : this.values)
			ret+=c.toString();
		ret+="]\n";
		return ret;
	}

}
