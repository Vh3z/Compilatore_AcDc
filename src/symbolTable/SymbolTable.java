package symbolTable;

import java.util.HashMap;

import ast.LangType;
import ast.TipoTD;
/**
 * La classe rappresenta la SymbolTable
 * 
 * @author marco
 */
public class SymbolTable {
	private static HashMap<String, Attributes> table;
	
	/**
	 * La classe rappresenta l'attributo
	 * 
	 * @author marco
	 */
	public static class Attributes {
		private LangType tipo;
		private char registro;
		
		/**
		 * Costruisce nuovo Attributo con tipo specificato
		 * 
		 * @param tipo il tipo dell'attributo
		 */
		public Attributes(LangType tipo) {
			this.tipo = tipo;
		}
		
		/**
		 * Restituisce il tipo dell'Attributo
		 *  
		 * @return il tipo 
		 */
		public LangType getTipo() {
			return tipo;
		}
		
		/**
		 * Imposta il registro 
		 * 
		 * @param registro il registro
		 */
		public void setRegister(char registro) {
			this.registro = registro;
		}
		
		/**
		 * Restituisce il registro
		 *  
		 * @return il registro
		 */
		public char getRegister() {
			return registro;
		}
	}
	
	/**
	 * Inizializza una nuova tabella dei simboli
	 */
	public static void init() { 
		table = new HashMap<>();
	}
	
	/**
	 * Inserisce una nuova voce nella tabella
	 * 
	 * @param id l'identificatore da inserire
	 * @param entry l'attributo associato all'identificatore
	 * @return True se inserimento corretto, False se non avviene
	 */
	public static boolean enter(String id, Attributes entry) {
		if(table.containsKey(id)) {
			return false;
		}
		else {
			table.put(id, entry);
			return true;
		}
	}
	
	/**
	 * cerca un identificatore e restituisce il suo attributo
	 * 
	 * @param id l'identificatore da cercare
	 * @return l'attributo associato all'identificatore
	 */
	public static Attributes lookup(String id) {
		return table.get(id);
	}
	
	/**
	 * Restituisce la symbolTable in formato di stringa
	 * 
	 * @return uns stringa che rappresenta la tabella
	 */
	public static String toStr() {
		String tableString ="";
		
		for(String key : table.keySet()) {
			tableString += key + ": " + table.get(key).toString();		
			}
		return tableString;
	}
	
	/**
	 * Restituisce la dimensione della tabella
	 * 
	 * @return la dimensione della tabella
	 */
	public static int Size() {
		return table.size();
	}
}
