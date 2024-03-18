package token;

/**
 * La classe rappresenta un token
 * 
 * @author marco
 */
public class Token {

	private int riga;
	private TokenType tipo;
	private String val;
	
	/** 
	 * Costruttore del token con relativo valore
	 * 
	 * @param tipo indica il tipo del token.
	 * @param riga indica la riga in cui si trova il token.
	 * @param val indica il valore del token.
	 */
	public Token(TokenType tipo, int riga, String val) {
		this.tipo = tipo;
		this.riga = riga; 
		this.val = val;
	}
	
	/**
	 * Costruttore del token senza valore
	 * 
	 * @param tipo indica il tipo del token. 
	 * @param riga indica la riga in cui si trova il token.
	 */
	public Token(TokenType tipo, int riga) {
		this.tipo = tipo;
		this.riga = riga;
		this.val = null;
	}

   /**
    * Restituisce il numero della riga in cui si trova il token
    * 
    * @return il numero della riga
    */
	public int getRiga() {
		return riga;
	}
	
	/**
	 * Restituisce il tipo del token
	 * 
	 * @return il tipo del token
	 */
	public TokenType getTipo() {
		return tipo;
	}
	
	/**
	 * Restituisce il valore associato al token
	 * 
	 * @return il valore associato al token
	 */
	public String getVal() {
		return val;
	}
	
	/**
	 * Restituisce il token in formato di stringa
	 * 
	 * @return la stringa che rappresenta il token
	 */
	@Override
	public String toString() {
	    if (this.val != null)
	        return "<" + tipo + ",riga:" + riga + "," + val + ">";
	    else
	        return "<" + tipo + ",riga:" + riga + ">";
	}
}
