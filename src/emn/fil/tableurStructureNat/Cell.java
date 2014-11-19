package emn.fil.tableurStructureNat;

import java.util.Observable;

public class Cell extends Observable{
	
	private Object value;	
	
	public Cell(Object value){
		this.value=value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers();
	}
	
	public String toString(){
		if (this.value!=null){
			return ""+this.value;
		}else{
			return "";
		}
	}	

}
