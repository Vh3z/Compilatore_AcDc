package visitor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Rappresenta i registri
 * 
 * @author marco
 */
public class Registri {
	/**
	 * Dichiara un ArrayList di caratteri
	 */
	public  static ArrayList<Character> registri;
	
	/**
	 * Inizializza la lista dei registri
	 */
	public static void initRegistri() {
		Character[] regs = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		registri = new ArrayList<Character>();
		registri.addAll(Arrays.asList(regs));
	}
	
	/**
	 * Restituisce un nuovo registro dalla lista dei registri che sono disponibili
	 * 
	 * @return un carattere che rappresenta il nuovo registro
	 */
	public static char newRegister() {
		if(registri.size() == 0) {
			throw new IllegalStateException("Nessun registro disponibile");
		}
		return registri.remove(0);
	}
}
