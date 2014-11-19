package emn.fil.tableurStructureNat;

import java.util.Observable;
import java.util.Observer;

public class Cell extends Observable implements Observer{
	
	private Object value;
	
	/**
	 * générée par formule
	 */
	private boolean isFromFormule;
	
	public Cell(Object value){
		this.value=value;
		this.isFromFormule=false;
	}
	
	public Cell(Object value, boolean isFromFormule){
		this.value=value;
		this.isFromFormule=isFromFormule;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean isFromFormule() {
		return isFromFormule;
	}

	public String toString(){
		if (this.value!=null){
			return ""+this.value;
		}else{
			return "";
		}
	}
	
	/**
	 * Réactualise la valeur de la Cell si elle a été initialisée par une formule
	 * et que la valeur calculée  a changé
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof FormuleToken){
			this.setValue(arg);
		}
	}	

}
