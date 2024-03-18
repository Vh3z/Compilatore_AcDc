package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeCost
 * 
 * @author marco
 */
public class NodeCost extends NodeExpr{
	private String value;
	private LangType type;
	
	/**
	 * Costruisce nuovo NodeCost con valore e tipo
	 * 
	 * @param value valore del nodo
	 * @param type tipo del nodo
	 */
	public NodeCost(String value, LangType type) {
		this.value = value;
		this.type = type;
	}
	
	/**
	 * Restituisce il valore
	 *  
	 * @return il valore
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Restituisce il tipo
	 *  
	 * @return il tipo
	 */
	public LangType getType() {
		return type;
	}
	
	/**
	 * Restituisce il NodeCost in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return "(" + getType().toString() + ")" + getValue();
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
