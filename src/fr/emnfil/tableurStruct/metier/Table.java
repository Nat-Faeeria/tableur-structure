package fr.emnfil.tableurStruct.metier;

public class Table {
	public Cell[] table;
	private String name;
	private int dimColumn;
	private int dimLine;
	
	public Table(String name){
		
	}
	
	public Table(String name, Vecteur vecteur1, Vecteur vecteur2){
		
		int x=vecteur1.values.length; int y=vecteur2.values.length;
		
		this.dimColumn=x+1; 
		this.dimLine=y+1;
		
		//multiplication de matrices pour créer un tableau de cells. On ajoute 1 pour pouvoir y integrer ces matrices.		
		table = new Cell[dimColumn*dimLine];
		
		table[0]=new Cell(0,"          ",this);
		//initialisation de la table
		for (int i=1;i<=table.length-1;i++){
			table[i]=new Cell(i,this);
		}
		
		//initialisation des "titres" de colonnes
		for (int i=1;i<=y;i++){
			table[i]=new ColumnCell(i,vecteur2.values[i-1],this);
		}
		
		//initialisation des "titres" de lignes
		for (int i=dimLine;i<=(x*dimLine);i+=dimLine){
			table[i]=new LineCell(i,vecteur1.values[(i-dimLine)/dimLine],this);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getDimColumn() {
		return dimColumn;
	}
	
	public int getDimLine() {
		return dimLine;
	}

	public Cell column(String idColumn){
		Cell column = this.getCellByValue(idColumn);
		if (column.isColumn()){
			return column;
		}
		return null;
	}
	
	public Cell line(String idLine){
		Cell line = this.getCellByValue(idLine);
		if (line.isLine()){
			return line;
		}
		return null;
	}
	
	public void put(int idLine, int idColumn, Object value){
		int position = (idLine*this.dimLine)-(this.dimLine-idColumn);
		table[position] = new Cell(position,value,this);
	}	
	
	/*private Cell getCellAt(int idLine, int idColumn){
		int position = (idLine*this.dimLine)-(this.dimLine-idColumn);
		return this.table[position];
	}*/	
	
	public Cell getCellByValue(Object value){
		for (Cell c : this.table){
			if (c.getValue().equals(value))
				return c;
		}
		return null;
	}
	
	public String toString(){
		int i=1;
		for (Cell c : this.table){
			if ((i%this.dimLine)!=0){
				System.out.print("("+c.toString()+")");
			}else{
				System.out.println("("+c.toString()+")");
			}
			i++;
		}
		return "Une table";
	}
}
