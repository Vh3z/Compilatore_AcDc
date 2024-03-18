package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodePrint
 * 
 * @author marco
 */
public class NodePrint extends NodeStm {
	private NodeId id;
	
	/**
	 * Costruisce nuovo NodePrint con identificatore
	 * 
	 * @param id identificatore del nodo
	 */
	public NodePrint(NodeId id) {
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
	 * Restituisce il NodePrint in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return "print " + id.toString();
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
