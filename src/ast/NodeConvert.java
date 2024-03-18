package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeConvert
 * 
 * @author marco
 */
public class NodeConvert extends NodeExpr {
	private NodeExpr exp;
	private LangType tipo;
	
	/**
	 * Costruisce nuovo NodeConvert con espressione e tipo
	 * 
	 * @param exp espressione del nodo
	 * @param tipo tipo del nodo
	 */
	public NodeConvert(NodeExpr exp, LangType tipo) {
		this.exp = exp;
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce l'espressione
	 *  
	 * @return l'espressione
	 */
	public NodeExpr getExp() {
		return exp;
	}
	
	/**
	 * Imposta l'espressione
	 *   
	 * @param exp l'espressione
	 */
	public void setExp(NodeExpr exp) {
		this.exp = exp;
	}
	
	/**
	 * Restituisce il tipo
	 *  
	 * @return il tipo
	 */
	public LangType getTipo() {
		return tipo;
	}
	
	/**
	 * Imposta il tipo
	 *  
	 * @param tipo il tipo
	 */
	public void setTipo(LangType tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce il NodeConvert in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return exp.toString();
	}
	
	/**
	 * Accetta il visitor
	 * 
	 * @param visitor il visitor da accettare
	 */
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
