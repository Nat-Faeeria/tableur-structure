package emn.fil.tableurStructureNat;

import java.io.IOException;

public class Test extends TableCreator {
	
	
	public Test() throws IOException{
		createTable("un");
		table("un").addColumns(DataTypes.INT, "un","deux","trois");
		table("un").column("un").addValues(1,5,6,7,8);
		table("un").column("deux").addValues(2,2);
		table("un").column("trois").addValues(3,2,3);
		table("un").applyFormule(Formule.SUM, "trois");
		table("un").applyFormule(Formule.AVERAGE, 2);
		
		
		createTable("deux");
		table("deux").addColumns(DataTypes.VARCHAR, "un","deux","trois");
		table("deux").column("un").addValues("a","b","c");
		table("deux").column("deux").addValues("a","b");
		table("deux").column("trois").addValues("a");
		
		join("trois", new String[]{"un","deux"}, "un","trois");
		
		insert("un",2,"trois",15);
		
		for (Table t : tables){
			System.out.println(t);
		}
		
		saveMeUp();
	}

	public static void main(String[] args) throws IOException {
		Test t = new Test();
	}

}