package visitor;

import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssing;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import symbolTable.SymbolTable;
import symbolTable.SymbolTable.Attributes;

/**
 * La classe rappresenta il generatore del codice
 * 
 * @author marco
 */
public class CodeGenerationVisitor implements IVisitor {
	private String codiceDC;
	private String log;
	ArrayList<String> codici = new ArrayList<>();
	
	/**
	 * Restituisce la lista dei codici generati durante la visita dell'AST
	 * 
	 * @return la lista dei codici generati
	 */
	public ArrayList<String> getCodici() {
		return codici;
	}
	
	/**
	 * Costruttore di CodeGenerationvisitor, inizializza la tabella dei simboli, i registri e il codice
	 */
	public CodeGenerationVisitor() {
		SymbolTable.init();
		Registri.initRegistri();
        this.codiceDC = "";
    }
	
	/**
	 * Restituisce il log degli errori
	 * 
	 * @return il log
	 */
	public String getLog() {
		return log;
	}
	
	/**
	 * Implementa la visita del NodeAssing, genera il codice per assegnare un valore a una variabile
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeAssing node) {
		NodeId Id = node.getId();
		NodeExpr exp = node.getExpr();
		
		Id.accept(this);
		String codiceId = codiceDC;
		exp.accept(this);
		String codiceExp = codiceDC;
		
		codiceDC = codiceExp + " s" + codiceId + " 0 k";
	}
	
	/**
	 * Implementa la visita del NodeBinOp, genera il codice l'operazione binaria
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeBinOp node) {
		NodeExpr left = node.getLeft();
		NodeExpr right = node.getRight();
		LangOper op = node.getOp();
		
		
		left.accept(this);
		String codiceLeft = codiceDC;
		right.accept(this);
		String codiceRight = codiceDC;
		String codiceOp;
		
		switch(op) {
		case PLUS:
			codiceOp = " + ";
			break;
		case MINUS:
			codiceOp = " - ";
			break;
		case TIMES:
			codiceOp = " * ";
			break;
		default:
			codiceOp = " / ";
		}
		codiceDC = codiceLeft + " " + codiceRight + codiceOp;
	}
	
	/**
	 * Implementa la visita del NodeConvert, genera il codice per la conversione di tipo
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeConvert node) {
		node.getExp().accept(this);
		
		codiceDC = "5 k " + codiceDC;		
	}
	
	/**
	 * Implementa la visita del NodeCost, genera il codice per le costanti
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeCost node) {
		String value = node.getValue();
		
		codiceDC = value;
	}
	
	/**
	 * Implementa la visita del NodeDecl, genera il codice per dichiarare una variabile
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeDecl node) {
		NodeId id = node.getId();
		NodeExpr exp = node.getInit();
		LangType tipo = node.getType();
		
		
		id.setAttributo(new Attributes(tipo));
		Attributes attributo = SymbolTable.lookup(id.getName());
		attributo.setRegister(Registri.newRegister());

		if(exp != null) {
			id.accept(this); 
			String codiceId = codiceDC;
			exp.accept(this);
			String codiceExp = codiceDC;
			
			codiceDC = " " + codiceExp + " s" + codiceId + " 0 k";
		}
		else codiceDC = "";
		
	}
	
	/**
	 * Implementa la visita del NodeDeref, genera il codice per accedere all'identificatore di una variabile
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeDeref node) {
		NodeId Id = node.getId();
		
		Id.accept(this);
		String codiceId = codiceDC;
		
		codiceDC = "l" + codiceId;
	}
	
	/**
	 * Implementa la visita del NodeId, genera il codice per accedere al valore di una variabile
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeId node) {
		char registro = SymbolTable.lookup(node.getName()).getRegister();
		
		codiceDC = registro + "";
	}
	
	/**
	 * Implementa la visita del NodePrint, genera il codice per stampare il valore di una variabile
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodePrint node) {
		NodeId Id = node.getId();
		
		Id.accept(this);
		String codiceId = codiceDC;
		
		codiceDC = "l" + codiceId + " p P ";
	}
	
	/**
	 * Implementa la visita del NodeProgram, passando per dichiarazioni e istruzioni
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeProgram node) {
		ArrayList<NodeDecSt> decSts = node.getDecSts();
		
		
		codiceDC = "";
		log = "";
		
		if(SymbolTable.Size() > Registri.registri.size()){
			log = "ERRORE";
		}
		for(NodeDecSt nodo : decSts) {
			nodo.accept(this);
			codici.add(codiceDC);
		}
	}

}
