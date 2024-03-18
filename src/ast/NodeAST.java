package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeAST
 * 
 * @author marco
 */
public abstract class NodeAST {
	
	/**
	 * Accetta il visitor
	 * 
	 * @param visitor il visitor da accettare
	 */
	public abstract void accept(IVisitor visitor);	
}
