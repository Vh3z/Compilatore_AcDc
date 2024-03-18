package ast;

import symbolTable.SymbolTable.Attributes;
import visitor.IVisitor;

/**
 * La classe rappresenta il NodeId
 * 
 * @author marco
 */
public class NodeId extends NodeAST {
	private String name;
	private Attributes attributo;
	
	/**
	 * Costruisce nuovo NodeId con nome del nodo
	 * 
	 * @param name il nome del nodo
	 */
	public NodeId(String name) {
		this.name = name;
	}
	
	/**
	 * Restituisce il nome del nodo
	 *  
	 * @return il nome
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Restituisce l'attributo associato al nodo
	 *  
	 * @return l'attributo
	 */
	public Attributes getAttributo() {
		return attributo;
	}
	
	/**
	 * Imposta l'attributo
	 *   
	 * @param attributo l'attributo 
	 */
	public void setAttributo(Attributes attributo) {
		this.attributo = attributo;
	}
	
	/**
	 * Restituisce il NodeId in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return getName();
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
