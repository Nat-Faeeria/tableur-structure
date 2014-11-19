package emn.fil.tableurStructureNat;

import java.util.List;

import javax.swing.text.StringContent;

public class Formule{
	
	public static final int AVERAGE = 1;
	public static final int SUM = 2;
	public static final int MIN = 3;
	public static final int MAX = 4;
	
	/**
	 * Permet d'exécuter une formule sur une liste de Cell
	 * Le but est d'être utilisé directement sur une Table.line, ou par une Column
	 * @param formule : Identifiant de la formule
	 * @param cells : liste de Cell sur laquelle appliquer la formule
	 * @return la valeur calculée par la formule
	 */
	public static Object execute(int formule, List<Cell> cells){
		if (formule==AVERAGE){
			return average(cells);
		}else if (formule==SUM){
			return sum(cells);
		}else if (formule==MIN){
			return min(cells);
		}else if (formule==MAX){
			return max(cells);
		}
		return null;
	}
	
	/**
	 * Permet d'exécuter une formule sur une Column.
	 * NE PAS UTILISER AVERAGE sur une Column !
	 * @param formule : Identifiant de la formule
	 * @param column : Column sur laquelle appliquer la formule
	 * @return la valeur calculée par la formule
	 */
	public static Object execute(int formule, Column column){
		if (formule!=AVERAGE){
			return execute(formule, column.getValues());
		}else{
			return "Pas appliquable sur une colonne";
		}
	}
	
	/**
	 * Formule pour calculer la moyenne d'une liste de Cell
	 * NE PAS UTILISER AVEC DES STRING
	 * @param cells : liste sur laquelle appliquer la formule
	 * @return la moyenne des valeurs des Cell
	 */
	private static Object average(List<Cell> cells){
		if (!(cells.get(0).getValue() instanceof String)){
			double average=0;
			if (cells.get(0).getValue() instanceof Integer){
				for (Cell c : cells){
					average+=(Integer)c.getValue();
				}
			}else{
				for (Cell c : cells){
					average+=(Double)c.getValue();
				}
			}
			return average/cells.size();
		}else{
			return "Pas de moyenne de chaines de caractères.";
		}
	}
	
	/**
	 * Formule pour calculer la somme d'une liste de Cell
	 * @param cells : liste sur laquelle appliquer la formule
	 * @return la somme des valeurs des Cell
	 */
	private static Object sum(List<Cell> cells){
		if (!(cells.get(0).getValue() instanceof String)){
			if (cells.get(0).getValue() instanceof Integer){
				Integer sum=0;
				for(Cell c : cells){
					sum+=(Integer)c.getValue();
				}
				return sum;
			}else{
				double sum=0;
				for(Cell c : cells){
					sum+=(Double)c.getValue();
				}
				return sum;
			}			
		}else{
			return "Pas de somme de chaines de caractères.";
		}
	}
	
	/**
	 * Renvoie la valeur minimale d'une liste de Cell
	 * @param cells : liste sur laquelle appliquer la formule 
	 * @return : la valeur minimale
	 */
	private static Object min(List<Cell> cells){
		if (!(cells.get(0).getValue() instanceof String)){
			if (cells.get(0).getValue() instanceof Integer){
				Integer min=(Integer) max(cells);
				for(Cell c : cells){
					Integer value = (Integer)c.getValue();
					if(min>value){
						min=value;
					}
				}
				return min;
			}else{
				Double min=(Double) max(cells);
				for(Cell c : cells){
					Double value = (Double)c.getValue();
					if(min>value){
						min=value;
					}
				}
				return min;
			}			
		}else{
			String min = (String) max(cells);
			for (Cell c : cells){
				String value = (String)c.getValue();
				if (min.length()>value.length()){
					min=value;
				}
			}
			return min;
		}
	}
	
	/**
	 * Renvoie la valeur maximale d'une liste de Cell
	 * @param cells : liste sur laquelle appliquer la formule 
	 * @return : la valeur maximale
	 */
	private static Object max(List<Cell> cells){
		if (!(cells.get(0).getValue() instanceof String)){
			if (cells.get(0).getValue() instanceof Integer){
				Integer max=0;
				for(Cell c : cells){
					Integer value = (Integer)c.getValue();
					if(max<value){
						max=value;
					}					
				}
				return max;
			}else{
				Double max=0.0;
				for(Cell c : cells){					
					Double value = (Double)c.getValue();
					if(max<value){
						max=value;
					}
				}
				return max;
			}
		}else{
			String max = new StringContent(100000000).toString();
			for (Cell c : cells){
				String value = (String)c.getValue();
				if (max.length()<value.length()){
					max=value;
				}
			}
			return max;
		}
	}

}
