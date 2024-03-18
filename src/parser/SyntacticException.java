package parser;

/**
 * La classe rappresenta l'errore di sintassi
 * 
 * @author marco
 */
public class SyntacticException extends Exception {
	
	/**
	 * crea un' istanza di SyntacticException con un messaggio di errore
	 * 
	 * @param string il messaggio di errore
	 */
	public SyntacticException(String string) {
		super(string);
	}
	
	/**
	 * crea un' istanza di SyntacticException con un messaggio di errore e una causa specifica
	 * 
	 * @param string il messaggio di errore
	 * @param cause la causa
	 */
	public SyntacticException(String string, Throwable cause) {
		super(string,cause);
	}
}
