package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeDecl
 * 
 * @author marco
 */
public class NodeDecl extends NodeDecSt {
	private NodeId id;
	private LangType type;
	private NodeExpr init;
	
	/**
	 * Costruisce nuovo NodeDecl con identificatore, tipo ed espressione 
	 * 
	 * @param id identificatore del nodo
	 * @param type tipo del nodo
	 * @param init espressione di inizializzazione
	 */
	public NodeDecl(NodeId id, LangType type, NodeExpr init) {
		this.id = id;
		this.type = type;
		this.init = init;
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
	 * Restituisce il tipo
	 *  
	 * @return il tipo
	 */
	public LangType getType() {
		return type;
	}
	
	/**
	 * Restituisce l'espressione di inizializzazione
	 *  
	 * @return l'espressione
	 */
	public NodeExpr getInit() {
		return init;
	}
	
	/**
	 * Restituisce il NodeDecl in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		String tp = "";
		
		switch(type) {
			case TYFLOAT: tp = "float";
			break;
			case TYINT: tp = "int";
			break;
		}
		
		String dec = "";
		
		if(init != null) {
			dec = init.toString();
		}
		
		return tp + " " + id.toString() + dec;
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
