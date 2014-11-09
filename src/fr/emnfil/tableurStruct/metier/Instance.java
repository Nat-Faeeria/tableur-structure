package fr.emnfil.tableurStruct.metier;

import java.util.ArrayList;

public class Instance {

	private static ArrayList<Table> tables;
	private static ArrayList<Vecteur> vecteurs;
	
	public Instance(){
		tables=new ArrayList<Table>();
		vecteurs=new ArrayList<Vecteur>();
	}
	
	public static void createTable(String name){
		tables.add(new Table(name));
	}
	
	public static void createTable(String name, Vecteur lines, Vecteur columns){
		tables.add(new Table(name,lines,columns));
	}
	
	public static void createVecteur(String name){
		vecteurs.add(new Vecteur(name));
	}
	
	public static void createVecteur(String name, Object[] values){
		vecteurs.add(new Vecteur(name,values));
	}
	
	public static Table table(String name){
		for (Table t : tables){
			if (t.name == name){
				return t;
			}
		}
		return null;
	}
	
	public static Vecteur vecteur(String name){
		for (Vecteur v : vecteurs){
			if (v.name == name){
				return v;
			}
		}
		return null;
	}
	
	public static void insert(Table table, Object idLine, Object idColumn, Object value){
		int positionLine = table.line(idLine).getPosition();
		int positionColumn = table.column(idColumn).getPosition();
		table.table[positionLine+positionColumn]=new Cell(positionLine+positionColumn,value,table);
	}
	

}
