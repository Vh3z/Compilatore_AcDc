package token;

/**
 * La classe rappresenta il tipo del Token
 * 
 * @author marco
 */
public enum TokenType {
	/**
	 * tipo token intero
	 */
	INT,
	
	/**
	 * tipo token float
	 */
	FLOAT,
	
	/**
	 * tipo token id
	 */
	ID,
	
	/**
	 * tipo token Tipointero
	 */
	TYINT,
	
	/**
	 * tipo token Tipofloat
	 */
	TYFLOAT,
	
	/**
	 * tipo token stampa
	 */
	PRINT,
	
	/**
	 * tipo token operazione di assegnamento
	 */
	OP_ASSIGN,
	
	/**
	 * tipo token somma
	 */
	PLUS,
	
	/**
	 * tipo token sottrazione
	 */
	MINUS,
	
	/**
	 * tipo token prodotto
	 */
	TIMES,
	
	/**
	 * tipo token divisione
	 */
	DIVIDE, 
	
	/**
	 * tipo token fine riga
	 */
	SEMI,
	
	/**
	 * tipo token fine del file
	 */
	EOF;
}
