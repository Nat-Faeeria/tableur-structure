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
	private List<FormuleToken> formuleTokens;
	
	public Table(String name){
		this.name=name;
		this.table=new Columns(this);
		this.table.add(0,new Column(DataTypes.INT, "lines"));
		this.formules=new Columns(this);
		this.formuleTokens=new ArrayList<FormuleToken>();
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
	 * Applique une formule sur une Column
	 * @param formule : la formule à appliquer
	 * @param columnName : la Column sur laquelle appliquer la formule
	 */
	public void applyFormule(int formule, String columnName){
		Column column = this.column(columnName);
		FormuleToken formuleToken = new FormuleToken(formule, column.getValues(), -1,columnName);
		for (Cell c : column.getValues()){
			c.addObserver(formuleToken);
		}
		Cell cell = new Cell(Formule.execute(formule, column),true);
		formuleToken.addObserver(cell);
		column.getValues().add(cell);
		this.formuleTokens.add(formuleToken);
	}
	
	/**
	 * Applique une formule sur une ligne
	 * @param formule : formule à appliquer
	 * @param indexLine : index de la ligne sur laquelle appliquer la formule > 0 !!!
	 */
	public void applyFormule(int formule, int indexLine){
		
		if (indexLine!=0){
			indexLine=indexLine-1;
		}
		List<Cell> cells = this.line(indexLine);
		
		//mémorisation d'application d'une formule
		FormuleToken formuleToken = new FormuleToken(formule, cells,indexLine,null);
		for (Cell c : cells){
			c.addObserver(formuleToken);
		}
		
		//application de la formule
		if (cells.get(0).getValue() instanceof String){
			Column column = new Column(DataTypes.VARCHAR, "Formule"+formule);
			for(int i=0;i<indexLine;i++){
				column.getValues().add(new Cell(""));
			}
			Cell cell = new Cell(Formule.execute(formule, cells),true);
			formuleToken.addObserver(cell);
			column.getValues().add(indexLine, cell);
			this.formules.add(column);
		}else{
			Column column = new Column(DataTypes.DOUBLE, "Formule"+formule);
			for(int i=0;i<indexLine;i++){
				column.getValues().add(new Cell(null));
			}
			Cell cell = new Cell(Formule.execute(formule, cells),true);
			formuleToken.addObserver(cell);
			column.getValues().add(indexLine, cell);
			this.formules.add(column);
			this.formuleTokens.add(formuleToken);
		}
	}
	
	public void applyFormule(int formule, List<Cell> cells){
		
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
		for (FormuleToken ft : this.formuleTokens){
			if (ft.indexLine!=-1 && ft.columnName==null){
				toReturn+="table(\""+this.name+"\").applyFormule("+ft.formule+", "+ft.indexLine+");\n";
			}else if (ft.indexLine==-1 && ft.columnName!=null){
				toReturn+="table(\""+this.name+"\").applyFormule("+ft.formule+", \""+ft.columnName+"\");\n";
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
