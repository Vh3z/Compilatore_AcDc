package ast;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeBinOp
 * 
 * @author marco
 */
public class NodeBinOp extends NodeExpr {
	private LangOper op;
	private NodeExpr left;
	private NodeExpr right;
	
	/**
	 * Costruisce nuovo NodeBinOp con operatore, espressione di sinistra e di destra
	 *  
	 * @param op operatore del node
	 * @param left espressione di sinistra
	 * @param right espressione di destra
	 */
	public NodeBinOp(LangOper op, NodeExpr left, NodeExpr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Restituisce l'operatore dell'operazione
	 *  
	 * @return l'operatore
	 */
	public LangOper getOp() {
		return op;
	}
	
	/**
	 * Restituisce l'espressione di sinistra
	 *  
	 * @return l'espressione di sinistra
	 */
	public NodeExpr getLeft() {
		return left;
	}
	
	/**
	 * Imposta l'espressione di sinsitra
	 *  
	 * @param left l'espressione di sinistra
	 */
	public void setLeft(NodeExpr left) {
		this.left = left;
	}
	
	/**
	 * Restituisce l'espressione di destra
	 *  
	 * @return l'espressione di destra
	 */
	public NodeExpr getRight() {
		return right;
	}
	
	/**
	 * Imposta l'espressione di destra
	 *  
	 * @param right l'espressione di destra
	 */
	public void setRight(NodeExpr right) {
		this.right = right;
	}
	
	/**
	 * Restituisce il NodeBinOp in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return "NodeBinOp [op=" + op + ", left=" + getLeft().toString() + ", right=" + getRight().toString() + "]";
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
