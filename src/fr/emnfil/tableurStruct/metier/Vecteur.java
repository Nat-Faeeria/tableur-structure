package fr.emnfil.tableurStruct.metier;

public class Vecteur {
	public final String name;
	public Object[] values;
	
	public Vecteur(String name){
		this.name=name;
	}
	
	public Vecteur(String name, Object[] values) {
		super();
		this.name = name;
		this.values = values;
	}
	
	public void addValues(Object[] values){
		this.values=values;
	}
}
