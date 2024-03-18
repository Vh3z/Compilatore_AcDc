package ast;

/**
 * La classe rappresenta il TypeDescriptor
 * 
 * @author marco
 */
public class TypeDescriptor {
	private TipoTD tipo;
	private String msg;
	
	/**
	 * Costruisce un nuovo TypeDescriptor con tipo e messaggio
	 * 
	 * @param tipo il tipo del TypeDescriptor
	 * @param msg il messaggio associato
	 */
	public TypeDescriptor(TipoTD tipo, String msg) {
		this.tipo = tipo;
		this.msg = msg;
	}
	
	/**
	 * Costruisce un nuovo TypeDescriptor con tipo
	 * 
	 * @param tipo il tipo del TypeDescriptor
	 */
	public TypeDescriptor(TipoTD tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce il tipo associato al TypeDescriptor
	 * 
	 * @return il tipo del TypeDescriptor
	 */
	public TipoTD getTipo() {
		return tipo;
	}
	
	/**
	 * Imposta il tipo associato al TypeDescriptor
	 * 
	 * @param tipo il tipo da associare al TypeDescriptor
	 */
	public void setTipo(TipoTD tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce il messaggio del typeDescriptor
	 * 
	 * @return il messaggio del TypeDescriptor
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Imposta il messaggio del TypeDescriptor
	 * 
	 * @param msg il messaggio del TypeDescriptor
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Verifica che il tipo del TypeDescriptor sia compatibile con quello di un altro
	 * 
	 * @param tD il Typedescriptor con cui verificare la compatibilità
	 * @return True se compatibili, False se non compatibili
	 */
	public boolean compatibile(TypeDescriptor tD) {
		if((this.tipo == tD.tipo  || (this.tipo == TipoTD.INT && tD.tipo == TipoTD.FLOAT)))
			return true;
		else return false;
	}
}
