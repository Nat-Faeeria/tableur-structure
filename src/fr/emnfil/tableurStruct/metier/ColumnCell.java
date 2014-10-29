package fr.emnfil.tableurStruct.metier;

public class ColumnCell extends Cell {

	public ColumnCell(int position, Object value, Table table) {
		super(position, value, table);
	}
	
	protected Cell[] getVecteur(){
		int dim = this.table.getDimLine();
		Cell[] retour = new Cell[this.table.getDimColumn()];
		for(int i=position,j=0;i<=(this.table.table.length-(dim-(position)));i+=dim,j++){
			retour[j]=this.table.table[i];
		}
		return retour;
	}
	
}
