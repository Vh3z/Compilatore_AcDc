package ast;

import java.util.ArrayList;

import visitor.IVisitor;

/**
 * La classe rappresenta il NodeProgram
 * 
 * @author marco
 */
public class NodeProgram extends NodeAST {
	private ArrayList<NodeDecSt> decSts;
	
	/**
	 * Costruisce nuovo NodeProgram con le dichiarzaioni di Statment
	 * 
	 * @param decSts la lista delle dichiarazione di Statment
	 */
	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		this.decSts = decSts;
	}
	
	/**
	 * Restituisce la lista delle dichiarazioni
	 *  
	 * @return la lista delle dichiarazioni
	 */
	public ArrayList<NodeDecSt> getDecSts() {
		return decSts;
	}
	
	/**
	 * Restituisce il NodeProgram in formato di stringa
	 * 
	 * @return una stringa che rappresenta il nodo
	 */
	@Override
	public String toString() {
		return "Program " + decSts.toString();
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
