package fr.emnfil.tableurStruct.metier;

public class Table {
	public Cell[] table;
	public final String name;
	private int dimColumn;
	private int dimLine;
	
	
	/****************************************
	****** Methode de construction de table n°1 **********
	************************************/
	
	/**
	 * Constructeur de table
	 * @param name : nom de la table
	 */
	public Table(String name){
		this.name = name;
	}
	
	/**
	 * Ajoute des dimensions (lignes et colonnes) à la table créée par un nom
	 * @param vecteurColumns : valeurs des entêtes de ligne
	 * @param vecteurLines : valeurs des entêtes de colonnes
	 */
	public void addDimensions(Vecteur vecteurLines, Vecteur vecteurColumns){
		
		int x=vecteurLines.values.length; int y=vecteurColumns.values.length;
		
		this.dimLine=x+1;
		this.dimColumn=y+1;
		
		//multiplication de matrices pour créer un tableau de cells. On ajoute 1 pour pouvoir y integrer ces matrices.
		table = new Cell[dimColumn*dimLine];
		table[0]=new Cell(0," ",this);
		
		//initialisation de la table
		for (int i=1;i<=table.length-1;i++){
			table[i]=new Cell(i,this);
		}
		
		//initialisation des "titres" de colonnes
		for (int i=1;i<=y;i++){
			table[i]=new ColumnCell(i,vecteurColumns.values[i-1],this);
		}
		
		//initialisation des "titres" de lignes
		for (int i=dimColumn;i<=(x*dimColumn);i+=dimColumn){
			table[i]=new LineCell(i,vecteurLines.values[(i-dimColumn)/dimColumn],this);
		}
	}
	
	
	
	/****************************************
	****** Methode de construction de table n°2 **********
	************************************/	
	
	/**
	 * Construit une table à partir de son nom, des entêtes de lignes et des entêtes de colonnes
	 * @param name : nom de la table
	 * @param vecteurColumns : valeurs des entêtes de ligne
	 * @param vecteurLines : valeurs des entêtes de colonnes
	 */
	public Table(String name, Vecteur vecteurLines, Vecteur vecteurColumns){
		
		this.name=name;
		
		int x=vecteurLines.values.length; int y=vecteurColumns.values.length;
		this.dimLine=x+1;
		this.dimColumn=y+1;
		//multiplication de matrices pour créer un tableau de cells. On ajoute 1 pour pouvoir y integrer ces matrices.
		table = new Cell[dimColumn*dimLine];
		table[0]=new Cell(0," ",this);
		//initialisation de la table
		for (int i=1;i<=table.length-1;i++){
			table[i]=new Cell(i,this);
		}
		//initialisation des "titres" de colonnes
		for (int i=1;i<=y;i++){
			table[i]=new ColumnCell(i,vecteurColumns.values[i-1],this);
		}
		//initialisation des "titres" de lignes
		for (int i=dimColumn;i<=(x*dimColumn);i+=dimColumn){
			table[i]=new LineCell(i,vecteurLines.values[(i-dimColumn)/dimColumn],this);
		}
	}
	
		
	/**
	 * Renvoie une cellule entête de colonne, permettant l'accès à la colonne
	 * (Classe ColumnCell)
	 * @param idColumn : entête de la colonne
	 * @return Une cellule entête de colonne
	 */
	public Cell column(Object idColumn){
		Cell column = this.getCellByValue(idColumn);
		if (column.isColumn()){
			return column;
		}
		return null;
	}
	
	/**
	 * Renvoie une cellue entête de ligne, donnant accès à la ligne
	 * (Classe LineCell)
	 * @param idLine : entête de la ligne
	 * @return Une cellule entête de ligne
	 */
	public Cell line(Object idLine){
		Cell line = this.getCellByValue(idLine);
		if (line.isLine()){
			return line;
		}
		return null;
	}
	
	/**
	 * Insère une valeur à l'intersection d'une ligne et d'une colonne
	 * @param idLine : entête ligne
	 * @param idColumn : entête colonne
	 * @param value : valeur à insérer
	 */
	public void put(int idLine, int idColumn, Object value){
		int position = (idLine*this.dimColumn)-(this.dimColumn-idColumn);
		table[position] = new Cell(position,value,this);
	}	
	
	/**
	 * Renvoie une cellule à partir de sa valeur
	 * @param value : valeur de la cellule recherchée
	 * @return la cellule contenant la valeur
	 */
	public Cell getCellByValue(Object value){
		for (Cell c : this.table){
			if (c.getValue().equals(value))
				return c;
		}
		return null;
	}
	
	
	
	/*********************
	*******Accesseurs***** 
	***********************/
	public int getDimColumn() {
		return dimColumn;
	}
	
	public int getDimLine() {
		return dimLine;
	}
	
	public String toString(){
		int i=1;
		for (Cell c : this.table){
			if ((i%this.dimColumn)!=0){
				System.out.print("("+c.toString()+")");
			}else{
				System.out.println("("+c.toString()+")");
			}
			i++;
		}
		return "Une table";
	}
}
