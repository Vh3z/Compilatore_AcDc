package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeAsing
 * 
 * @author marco
 */
public class NodeAssing extends NodeStm{
	private NodeId id;
	private NodeExpr expr;
	
	/**
	 * Costruisce nuovo NodeAssing con identificatore e espressione 
	 * 
	 * @param id identificatore
	 * @param expr espressione da associare
	 */
	public NodeAssing(NodeId id, NodeExpr expr) {
		this.id = id;
		this.expr =expr;
	}
	
	/**
	 * Restituisce l'identificatore dell'assegnamento
	 * 
	 * @return identificatore
	 */
	public NodeId getId() {
		return id;
	}
	
	/**
	 * Restituisce l'espressione dell'assegnamento
	 * 
	 * @return espressione
	 */
	public NodeExpr getExpr() {
		return expr;
	}
	
	/**
	 * Restituisce il NodeAssing in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return getId().toString() + " = " + getExpr().toString();
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
