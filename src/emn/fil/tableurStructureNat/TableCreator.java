package emn.fil.tableurStructureNat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;


/**
 * Classe qui permet de créer des tableaux
 * Elle doit être héritée par une classe, puis les méthodes de création doivent être utilisées
 * dans le constructeur, comme quand on utilise un JFrame. Mais on peut créer plusieurs tableaux
 * avec une seul sous-classe de TableCreator
 * @author dev Nat-Faeeria
 *
 */
public abstract class TableCreator {
	public static Tables tables;
	
	public TableCreator(){
		tables=new Tables();
	}
	
	/**
	 * Crée une table et la stocke dans la liste des Table
	 * @param name : nom de la table
	 */
	public static void createTable(String name){
		tables.add(new Table(name));
	}
	
	/**
	 * Donne accès à une table stockée dans la liste des Table
	 * @param name : le nom de la table demandée
	 * @return la table demandée
	 */
	public static Table table(String name){
		return tables.getTableByName(name);
	}
	
	/**
	 * Insère une valeur dans une cellule sur une ligne et une colonne
	 * @param tableName : nom de la Table
	 * @param indexLine : index de la ligne
	 * @param columnName : nom de la Column
	 * @param value : valeur à insérer
	 */
	public static void insert(String tableName, int indexLine, Object columnName, Object value){
		Table table = tables.getTableByName(tableName);
		table.column(columnName).insert(indexLine-1, value);
	}
	
	/**
	 * Equivalent de SELECT en SQL, permets de créer une nouvelle table par agrégation de colonnes
	 * d'autres tables
	 * @param newTableName : nom de la table à créer
	 * @param tableNames : tableau de noms des tables à utiliser
	 * @param columnNames : nom des colonnes à utiliser
	 */
	public static void join(String newTableName, String[] tableNames, Object... columnNames){
		Table newTable = new Table(newTableName);;
		
		for (String tableName : tableNames){
			Table table = tables.getTableByName(tableName);
			for (Object name : columnNames){
				Column column = table.column(name);
				if (column!=null){
					newTable.addColumn(column);
					newTable.updateLines(column.size());
				}
			}
		}
		
		tables.add(newTable);
	}
	
	/**
	 * Méthode de "persistance" en code Java, faut pas trop chercher. Crée un fichier (dégueulasse)
	 */
	public static void saveMeUp() throws IOException{
		String date = ""+LocalDate.now();
		date=date.replace("-", "");
		String datasToSave = "package emn.fil.tableurStructureNat;\n";
		datasToSave+="public class TableurDu"+date+" extends TableCreator {\n";
		datasToSave+="public TableurDu"+date+"(){\n";
		for(Table t : tables){
			datasToSave+=t.saveMeUp();
		}
		datasToSave+="}\n}";
		
		FileOutputStream fos = new FileOutputStream(new File("TableurDu"+date+".java"));
		fos.write(datasToSave.getBytes());
		fos.close();
	}

}
