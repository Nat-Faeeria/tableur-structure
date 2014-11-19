package emn.fil.tableurStructureNat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Table implements Observer{
	
	private String name;
	private Columns table;
	private Columns formules;
	
	public Table(String name){
		this.name=name;
		this.table=new Columns(this);
		this.table.add(0,new Column(DataTypes.INT, "lines"));
		this.formules=new Columns(this);
	}
	
	/**
	 * renvoie un objet Column en fonction de son nom
	 * @param columnName : nom de la Column
	 * @return la Column recherchée
	 */
	public Column column(Object columnName){
		return this.table.findColumnByName(columnName);
	}
	
	/**
	 * Renvoie une liste de Cell correspondant à une ligne
	 * @param index : index de la ligne à récupérer
	 * @return : la ligne
	 */
	public List<Cell> line(int index){
		List<Cell> cells = new ArrayList<Cell>();
		for (Column c : this.table){
			if (c.getName()!="lines"){
				cells.add(c.getValues().get(index));
			}
		}
		return cells;
	}
	
	/**
	 * Applique une formule sur une ligne
	 * @param formule : formule à appliquer
	 * @param indexLine : index de la ligne sur laquelle appliquer la formule
	 */
	public void applyFormule(int formule, int indexLine){
		List<Cell> cells = this.line(indexLine-1);
		if (cells.get(0).getValue() instanceof String){
			Column column = new Column(DataTypes.VARCHAR, "Formule"+formule);
			for(int i=0;i<indexLine-1;i++){
				column.getValues().add(new Cell(""));
			}
			Cell cell = new Cell(Formule.execute(formule, cells));
			column.getValues().add(indexLine-1, cell);
			this.formules.add(column);
		}else{
			Column column = new Column(DataTypes.DOUBLE, "Formule"+formule);
			for(int i=0;i<indexLine-1;i++){
				column.getValues().add(new Cell(null));
			}
			Cell cell = new Cell(Formule.execute(formule, cells));
			column.getValues().add(indexLine-1, cell);
			this.formules.add(column);
		}
	}
	
	/**
	 * Applique une formule sur une Column
	 * @param formule : la formule à appliquer
	 * @param columnName : la Column sur laquelle appliquer la formule
	 */
	public void applyFormule(int formule, String columnName){
		Column column = this.column(columnName);
		column.addValue(Formule.execute(formule, column));
	}
	
	/**
	 * Ajoute une Column dans la liste des Column
	 * @param column :la Column
	 */
	public void addColumn(Column column){
		column.addObserver(this);
		this.table.add(column);
	}
	
	/**
	 * Crée une Column par nom de colonne entrée et l'ajoute dans la liste des Columns
	 * @param type : type des Column
	 * @param columnNames : noms des Column
	 */
	public void addColumns(DataTypes type, Object... columnNames){
		table.addColumns(type,columnNames);
	}
	
	/**
	 * Crée une Column par nom de colonne dans la liste et l'ajoute dans la liste des Columns
	 * @param type : type des Column
	 * @param columnNames : liste des noms de Column
	 */
	public void addColumns(DataTypes type,List<Object> columnNames){
		table.addColumns(type,columnNames);
	}
	
	/**
	 * Permet de récupérer tous les noms de Column
	 * @return une liste de tous les noms des Column
	 */
	public List<Object> getColumnNames(){
		return this.table.getColumnNames();
	}
	
	/**
	 * Récupère le nom de la table
	 * @return le nom de la table
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Méthode de "persistance" en code Java
	 * @return le code nécessaire, faut pas trop chercher
	 */
	public String saveMeUp(){
		String toReturn = "createTable(\""+this.name+"\");\n";
		for (Column c : this.table){
			if (c.getName()!="lines"){
				toReturn+="table(\""+this.name+"\").addColumn("+c.saveMeUp()+");\n";
				toReturn+="table(\""+this.name+"\").column(\""+c.getName()+"\").addValues("+c.getCellValues()+");\n";
			}
		}
		for (Column c : this.formules){
			if (c.getName()!="lines"){
				toReturn+="table(\""+this.name+"\").addColumn("+c.saveMeUp()+");\n";
				toReturn+="table(\""+this.name+"\").column(\""+c.getName()+"\").addValues("+c.getCellValues()+");\n";
			}
		}
		return toReturn;
	}
	
	/**
	 * Permet d'ajouter un numéro de ligne 
	 * à chaque fois qu'une Column a une ligne de plus que la Column des lignes
	 * @param size : la taille de la Column modifiée
	 */
	public void updateLines(int size){
		int i = this.column("lines").size();
		while (i<size){
			i++;
			this.column("lines").addValue(i);				
		}
	}
	
	/**
	 * Méthode pourrie à supprimer quand plus besoin qui permet de visualiser le tableau
	 */
	@Override
	public String toString() {
		return this.name+"\n"+this.table.toString()+this.formules.toString();
	}

	/**
	 * Méthode d'update pour les observer
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Column){
			this.updateLines((int)arg);
		}
	} 
	
}
