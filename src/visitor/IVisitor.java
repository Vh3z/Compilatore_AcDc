package visitor;

import ast.*;

/**
 * Interfaccia IVisitor
 * 
 * @author marco
 */
public interface IVisitor {
	/**
	 * vsita un nodo assegnamento
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeAssing node);
	
	/**
	 * vsita un nodo di operazione binaria
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeBinOp node);
	
	/**
	 * vsita un nodo di conversione
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeConvert node);
	
	/**
	 * vsita un nodo di una costante
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeCost node);
	
	/**
	 * vsita un nodo di dichiarazione
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeDecl node);
	
	/**
	 * vsita un nodo dereferenziatore
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeDeref node);
	
	/**
	 * vsita un nodo che contiene l'id
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeId node);
	
	/**
	 * vsita un nodo adi stampa
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodePrint node);
	
	/**
	 * vsita un nodo Program
	 * 
	 * @param node il nodo da visistare
	 */
	public abstract void visit(NodeProgram node);
}
