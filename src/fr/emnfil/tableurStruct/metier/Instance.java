package fr.emnfil.tableurStruct.metier;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<Table> tables;
	private ArrayList<Vecteur> listeValeurs;
	
	public Instance(){
		this.tables=new ArrayList<Table>();
	}
	
	public void createTable(String name){
		this.tables.add(new Table(name));
	}

}
