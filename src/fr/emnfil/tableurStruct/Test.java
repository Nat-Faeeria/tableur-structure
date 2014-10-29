package fr.emnfil.tableurStruct;

import fr.emnfil.tableurStruct.metier.Table;
import fr.emnfil.tableurStruct.metier.Vecteur;

public class Test {

	public static void main(String[] args) {
		Vecteur vecteur1 = new Vecteur("un",new String[]{"mat","pie","fra","jph"});
		Vecteur vecteur2 = new Vecteur("deux",new String[]{"maths","français"});
		
		Table table = new Table("Un",vecteur1,vecteur2);
		
		table.put(2, 1, "10");
		table.put(2,2,"5");
		
		table.column("maths").insert("mat", "reussi");
		table.line("pie").insert("français", "rate");
		table.line("pie").insertValues("c1","c2");
		table.column("maths").insertValues("l1","l2","l3","l4");
		
		
		table.toString();

	}

}
