package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeDeref
 * 
 * @author marco
 */
public class NodeDeref extends NodeExpr{
	private NodeId id;
	
	/**
	 * Costruisce nuovo NodeDeref con identificatore
	 * 
	 * @param id l'identificatore 
	 */
	public NodeDeref(NodeId id) {
		this.id = id;
	}
	
	/**
	 * Restituisce l'identificatore
	 *  
	 * @return l'identificatore
	 */
	public NodeId getId() {
		return id;
	}
	
	/**
	 * Restituisce il NodeDeref in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return getId().toString();
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
