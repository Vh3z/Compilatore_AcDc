package ast;

/**
 * La classe rappresenta il tipo del TypeDescriptor
 * 
 * @author marco
 */
public enum TipoTD {
	
	/**
	 * tipo intero
	 */
	INT,
	
	/**
	 * tipo float
	 */
	FLOAT,
	
	/**
	 * tipo Ok, operazione andata a buon fine
	 */
	OK,
	
	/**
	 * tipo errore, c'è stato un errore
	 */
	ERROR;
}
