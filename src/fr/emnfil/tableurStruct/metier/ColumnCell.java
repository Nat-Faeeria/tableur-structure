package fr.emnfil.tableurStruct.metier;

public class ColumnCell extends Cell {

	public ColumnCell(int position, Object value, Table table) {
		super(position, value, table);
	}
	
	/**
	 * Renvoie une vecteur correspondant à la colonne
	 */
	protected Cell[] getVecteur(){
		int dim = this.table.getDimColumn();
		Cell[] retour = new Cell[this.table.getDimLine()];
		for(int i=position,j=0;i<=(this.table.table.length-(dim-(position)));i+=dim,j++){
			retour[j]=this.table.table[i];
		}
		return retour;
	}
	
}
