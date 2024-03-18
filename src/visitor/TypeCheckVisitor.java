package visitor;


import java.util.ArrayList;

import ast.*;
import symbolTable.SymbolTable;
import symbolTable.SymbolTable.Attributes;

/**
 * La classe rappresenta il TypeCheckVisitor
 * 
 * @author marco
 */
public class TypeCheckVisitor implements IVisitor {
	private TypeDescriptor resType;
	
	/**
	 * Costruttore del TypeCheckvisitore, inizializza una tabella e setta il resType
	 */
	public TypeCheckVisitor() {
		SymbolTable.init();
		resType = new TypeDescriptor(TipoTD.OK,"");
	}
	
	/**
	 * Restituisce il Typedescriptor
	 * 
	 * @return il Typedescriptor della visita
	 */
	public TypeDescriptor getResType() {
		return resType;
	}
	
	/**
	 * Implementa la visita del NodeAssing, esegue il controllo dei tipi tra identificatore e espressione
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeAssing node) {
		NodeId id = node.getId();
		NodeExpr exp = node.getExpr();
		
		id.accept(this);
		TypeDescriptor idTD = new TypeDescriptor(resType.getTipo(),resType.getMsg());
		exp.accept(this);
		TypeDescriptor expTD = resType;
		
		
		if(!expTD.compatibile(idTD) && expTD.getTipo() != TipoTD.ERROR) {
			resType.setTipo(TipoTD.ERROR);
			resType.setMsg("I tipi dell'assegnamento non sono compatibili");
		}else {
			if(expTD.getTipo() != idTD.getTipo() && expTD.getTipo() != TipoTD.ERROR) {
				NodeConvert converti = new NodeConvert(exp,LangType.TYFLOAT);
				exp = converti;
				exp.accept(this);
			}
			resType.setTipo(expTD.getTipo());
		}
	}
	
	/**
	 * Implementa la visita del NodeBinOp, esegue il controllo dei tipi tra gli operandi di destra e di sinistra
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeBinOp node) {
		NodeExpr left = node.getLeft();
		NodeExpr right = node.getRight();
		
		left.accept(this);
		TypeDescriptor leftTD = resType;
		right.accept(this);
		TypeDescriptor rightTD = resType;
		
		if(leftTD.getTipo() == TipoTD.ERROR) {
			resType.setTipo(TipoTD.ERROR);
			resType.setMsg(leftTD.getMsg());
		}else if(rightTD.getTipo() == TipoTD.ERROR) {
			resType.setTipo(TipoTD.ERROR);
			resType.setMsg(rightTD.getMsg());
		}else if(rightTD.getTipo() != leftTD.getTipo()) {
			if(rightTD.getTipo() != TipoTD.FLOAT) {
				NodeConvert converti = new NodeConvert(right,LangType.TYFLOAT);
				node.setRight(converti);
			}
			else {
				NodeConvert converti = new NodeConvert(left,LangType.TYFLOAT);
				node.setLeft(converti);
			}
			resType.setTipo(leftTD.getTipo());
		}
	}
	
	/**
	 * Implementa la visita del NodeConvert
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeConvert node) {
		NodeExpr exp = node.getExp();
		
		exp.accept(this);
	}
	
	/**
	 * Implementa la visita del NodeCost, impostando il tipo
	 *  
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeCost node) {
		LangType tipo = node.getType();
		
		if(tipo == LangType.TYFLOAT)
			resType = new TypeDescriptor(TipoTD.FLOAT);
		else
			resType = new TypeDescriptor(TipoTD.INT);
	}
	
	/**
	 * Implementa la visita del Nodedecl,controlla che l'identificatore non sia già presente nella tabella, nel caso non lo sia lo inserisce
	 * Inoltre controlla anche la presenza di un espressione d'inizializzazione
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeDecl node) {
		NodeId id = node.getId();
		LangType tipo = node.getType();
		NodeExpr exp = node.getInit();
		
		if(SymbolTable.lookup(id.getName()) == null){
			SymbolTable.enter(id.getName(), new Attributes(tipo));
		}else {
			resType.setTipo(TipoTD.ERROR);
			resType.setMsg("Id già dichiarato");
		}
		
		if(exp != null) {
			exp.accept(this);
		}
	}
	
	/**
	 * Implementa la visita del NodeDeref
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeDeref node) {
		NodeId id = node.getId();
		
		id.accept(this);
	}
	
	/**
	 * Implementa la visita del NodeId,controlla che l'identificatore non sia già presente nella tabella,
	 * imposta il tipo in base a quello dell'identificatore
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeId node) {
		String nome = node.getName();
		
		if(SymbolTable.lookup(nome) == null) {
			resType.setTipo(TipoTD.ERROR);
			resType.setMsg("Id non dichiarato");
		
		}else {
			if(SymbolTable.lookup(nome).getTipo() == LangType.TYFLOAT) {
				resType.setTipo(TipoTD.FLOAT);
			}else {
				resType.setTipo(TipoTD.INT);
			}	
		}
	}
	
	/**
	 * Implementa la visita del NodePrint, verifica che l'identificatore sia dichiarato e imposta il risultato di conseguenza
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodePrint node) {
		NodeId id = node.getId();
		
		id.accept(this);
		
		if(resType.getTipo() == TipoTD.ERROR) {
			resType.setMsg("Id non dichiarato");
		}else {
			resType.setTipo(TipoTD.OK);
			resType.setMsg("OK");
		}
	}
	
	/**
	 * Implementa la visita del NodeProgram, passando per dichiarazioni e istruzioni
	 * 
	 * @param node il nodo da visitare
	 */
	@Override
	public void visit(NodeProgram node) {
		ArrayList<NodeDecSt> decSts = node.getDecSts();
		
		for(NodeDecSt nodo : decSts) {
			nodo.accept(this);
		}
	}
}
