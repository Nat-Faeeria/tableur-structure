package fr.emnfil.tableurStruct.metier;

public class LineCell extends Cell {

	public LineCell(int position, Object value, Table table) {
		super(position, value, table);
	}
	
	protected Cell[] getVecteur(){
		int dim = this.table.getDimLine();
		Cell[] retour = new Cell[dim];
		for(int i=position,j=0;i<(position+dim);i+=1,j++){
			retour[j]=this.table.table[i];
		}
		return retour;
	}

}
