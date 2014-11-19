package emn.fil.tableurStructureNat;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Permet de garder en mémoire l'application d'une formule
 * @author dev
 *
 */
public class FormuleToken extends Observable implements Observer{
	
	/**
	 * La formule appliquée
	 */
	public final int formule;
	/**
	 * les Cell impactées
	 */
	private List<Cell> cells;
	public final int indexLine;
	public final String columnName;
	private Object value;
	
	public FormuleToken(int formule, List<Cell> cells, int indexLine, String columnName) {
		super();
		this.formule = formule;
		this.cells = cells;
		this.indexLine=indexLine;
		this.columnName=columnName;
	}
	
	

	public void setValue(Object value) {
		this.value = value;
		this.setChanged();
		notifyObservers(this.value);
	}



	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Cell){
			this.setValue(Formule.execute(formule, cells));
		}
	}
	
	
}
