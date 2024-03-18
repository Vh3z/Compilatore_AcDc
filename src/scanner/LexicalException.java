package scanner;

/**
 * La classe rappresenta l'errore lessicale
 * 
 * @author marco
 */
public class LexicalException extends Exception {
	
	/**
	 * crea un' istanza di LexicalException con un messaggio di errore
	 * 
	 * @param string il messaggio di errore
	 */
	public LexicalException(String string) {
		super(string);
	}
	
	/**
	 * crea un' istanza di LexicalException con un messaggio di errore e una causa specifica
	 * 
	 * @param string il messaggio di errore
	 * @param cause la causa
	 */
	public LexicalException(String string,  Throwable cause) {
		super(string, cause);
	}
}
