package fr.emnfil.tableurStruct.metier;

public class LineCell extends Cell {

	public LineCell(int position, Object value, Table table) {
		super(position, value, table);
	}
	
	/**
	 * Renvoie un vecteur correspondant à la ligne
	 */
	protected Cell[] getVecteur(){
		int dim = this.table.getDimColumn();
		Cell[] retour = new Cell[dim];
		for(int i=position,j=0;i<(position+dim);i+=1,j++){
			retour[j]=this.table.table[i];
		}
		return retour;
	}

}
