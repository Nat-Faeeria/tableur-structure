package fr.emnfil.tableurStruct.metier;

public class Cell {
	
	protected int position;
	protected Object value;
	protected Table table;
	
	public Cell(int position,Table table){
		this.position=position;
		this.value=null;
		this.table=table;
	}
	
	public Cell(int position, Object value, Table table){
		this.position = position;
		this.value = value;
		this.table = table;
	}
	
	public int getPosition() {
		return position;
	}

	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	protected Cell[] getVecteur(){
		return null;
	}
	
	public void insert(String id, Object value){
		if (isColumn() || isLine()){
			int position = this.table.getCellByValue(id).getPosition();
			this.table.table[position+this.position]=new Cell(position+this.position,value,this.table);
		}
	}

	public void insertValues(Object... values){
		Cell[] vecteur = this.getVecteur();
		int len = vecteur.length-1;
		if (vecteur!=null && len==values.length){
			for(int i=0;i<len;i++){
				vecteur[i+1].setValue(values[i]);
			}
		}
	}
	
	public boolean isColumn(){
		return (this instanceof ColumnCell);
	}
	
	public boolean isLine(){
		return (this instanceof LineCell);
	}

	public String toString(){
		if (this.value==null){
			return "Une cell  ";
		}else{
			String retour = this.value.toString();
			if (retour.length()<10){				
				for (int i=1;i<=(10-retour.length());i++){
					retour.concat(" ");
				}
				return retour;
			}else{
				return retour;
			}
		}
	}

}