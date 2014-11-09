package fr.emnfil.tableurStruct;

import fr.emnfil.tableurStruct.metier.Instance;
import fr.emnfil.tableurStruct.metier.Table;
import fr.emnfil.tableurStruct.metier.Vecteur;

public class Test extends Instance{
	
	public Test(){
		createVecteur("un",new String[]{"mat","pie","fra","jph"});
		createVecteur("deux",new String[]{"maths","français"});
		
		createTable("un", vecteur("un"), vecteur("deux"));
		table("un").put(2, 1, "10");
		table("un").put(2,2,"5");
		table("un").line("pie").insert("français", "rate");
		table("un").column("maths").insertValues("l1","l2","l3","l4");
		table("un").toString();
		
		createVecteur("trois",new String[]{"a","b","c","d"});
		createVecteur("quatre",new String[]{"e","f"});
		createTable("deux");
		table("deux").addDimensions(vecteur("trois"),vecteur("quatre"));
		
		insert(table("deux"),"a","e","test");
		table("deux").toString();
		
		createTable("trois", vecteur("quatre"),vecteur("trois"));
		table("trois").toString();
	}

	public static void main(String[] args) {
		Test t = new Test();
	}

}
