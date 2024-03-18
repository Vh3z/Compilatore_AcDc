package parser;

//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
//import java.io.PushbackReader;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;


import token.*;
import scanner.*;
import ast.*;

/**
 * La classe rappresenta il parser
 * 
 * @author marco
 */
public class Parser {
	
	private Scanner scanner;
	
	/**
	 * Costruttore della classe parser
	 * 
	 * @param scanner lo scanner associato a questo Parser
	 */
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	private Token match(TokenType type) throws LexicalException, SyntacticException, IOException{
		Token tk = scanner.peekToken();
		
		if(type.equals(tk.getTipo())) return scanner.nextToken();
		else throw new SyntacticException("aspettato token " + type + " alla riga " + tk.getRiga());
	}
	
	/**
	 * Avvia il Parsing
	 * 
	 * @return il programma come AST
	 * @throws LexicalException lancia un eccezione lessicale
	 * @throws SyntacticException lancia un eccezione sintattica
	 * @throws IOException lancia un eccezione di input o output
	 */
	public NodeProgram parse() throws LexicalException, SyntacticException, IOException{
		return parsePrg();
	}
	
	
	private NodeProgram parsePrg() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		
		switch(tk.getTipo()) {
			case TYFLOAT, TYINT, ID, PRINT, EOF:{
				ArrayList<NodeDecSt> decSts = parseDSs();
				match(TokenType.EOF);
				return new NodeProgram(decSts);
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " non e' inizio di programma");
		}	
	}
	
	private ArrayList<NodeDecSt> parseDSs()throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case TYFLOAT, TYINT:{
				NodeDecSt Dec = parseDcl();
				ArrayList<NodeDecSt> DSs = parseDSs();
				DSs.add(0, Dec);
				return DSs;
			}
			case ID, PRINT:{
				NodeDecSt Stm = parseStm();
				ArrayList<NodeDecSt> DSs = parseDSs();
				DSs.add(0, Stm);
				return DSs;
			}
			case EOF:
				return new ArrayList<NodeDecSt>();	
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " non puo' essere inizio di riga");
		}
	}
	
	private NodeDecl parseDcl() throws LexicalException, SyntacticException, IOException{ 
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case TYFLOAT, TYINT:{
				LangType type = parserTy();
				Token var = match(TokenType.ID);
				NodeId Id = new NodeId(var.getVal());
				NodeExpr expr = parserDclP();
				return new NodeDecl(Id,type,expr);
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " non puo' essere inizio di riga");
		}
	}
	
	private LangType parserTy() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case TYFLOAT:{
				match(TokenType.TYFLOAT);
				return LangType.TYFLOAT;
			}
			case TYINT:{
				match(TokenType.TYINT);
				return LangType.TYINT;
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " non e' inizio di programma");
		}
	}
	
	private NodeExpr parserDclP() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case SEMI:{
				match(TokenType.SEMI);
				return null;
			}
			case OP_ASSIGN:{
				match(TokenType.OP_ASSIGN);
				NodeExpr expr = parseExp();
				match(TokenType.SEMI);
				return expr;
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere o ';' o un operatore di assegnamento!");
		}
	}
	
	private NodeStm  parseStm() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case ID:{
				Token var = match(TokenType.ID);
				Token OpAss = this.scanner.peekToken();
				NodeId Id = new NodeId(var.getVal());
				match(TokenType.OP_ASSIGN);
				if(OpAss.getVal().equals("=")) {
					NodeExpr exp1 = parseExp();
					match(TokenType.SEMI);
					return new NodeAssing(Id,exp1);
				}else {
					NodeExpr exp = parseExp();
					NodeBinOp newOp;
					
					switch(OpAss.getVal()) {
					case "+=":{
						newOp = new NodeBinOp(LangOper.PLUS,new NodeDeref(Id),exp);
						break;
						}
					case "-=":{
						newOp = new NodeBinOp(LangOper.MINUS,new NodeDeref(Id),exp);
						break;
						}
					case "*=":{
						newOp = new NodeBinOp(LangOper.TIMES,new NodeDeref(Id),exp);
						break;
						}
					default:{
						newOp = new NodeBinOp(LangOper.DIVIDE,new NodeDeref(Id),exp);
						}
					}
					match(TokenType.SEMI);
					return new NodeAssing(Id,newOp);
					
				}
				
			}
			case PRINT:{
				match(TokenType.PRINT);
				Token var = match(TokenType.ID);
				NodeId Id = new NodeId(var.getVal());
				match(TokenType.SEMI);
				return new NodePrint(Id);
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un id o print");
		}
	}
	
	private NodeExpr parseExp() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case ID, FLOAT, INT:{
				NodeExpr left = parseTr();
				NodeExpr exp1 = parseExpP(left);
				return exp1;
     		}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un valore(intero o decimale) oppure un id");
		}
	}
	
	private NodeExpr parseExpP(NodeExpr left) throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case PLUS:{
				match(TokenType.PLUS);
				LangOper op = LangOper.PLUS;
				NodeExpr exp1 = parseTr();
				NodeExpr exp2 = parseExpP(exp1);
				return new NodeBinOp(op,left,exp2);
			}
			case MINUS:{
				match(TokenType.MINUS);
				LangOper op = LangOper.MINUS;
				NodeExpr exp1 = parseTr();
				NodeExpr exp2 = parseExpP(exp1);
				return new NodeBinOp(op,left,exp2);
			}
			case SEMI:
				return left;
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un valore(intero o decimale) oppure un id");
		}
	} 
	
	private NodeExpr parseTr() throws  LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case ID,FLOAT,INT:{
				NodeExpr left = parseVal();
				NodeExpr exp1 = parseTrP(left);
				return exp1;
				}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un valore(intero o decimale) oppure un ID");
		}
	}
	
	private NodeExpr parseTrP(NodeExpr left) throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case TIMES:{
				match(TokenType.TIMES);
				LangOper op = LangOper.TIMES;
				NodeExpr exp1 = parseVal();
				NodeExpr exp2 = parseTrP(exp1);
				return new NodeBinOp(op,left,exp2);
			}
			case DIVIDE:{
				match(TokenType.DIVIDE);
				LangOper op = LangOper.DIVIDE;
				NodeExpr exp1 = parseVal();
				NodeExpr exp2 = parseTrP(exp1);
				return new NodeBinOp(op,left,exp2);
			}
			case MINUS, PLUS,SEMI:{
				return left;
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un operatore(non di assegnamento) oppure ';'");
		}
	}
	
	private NodeExpr parseVal() throws LexicalException, SyntacticException, IOException{
		Token tk;
		try {
			tk = scanner.peekToken();
		}catch(LexicalException e) {
			throw new SyntacticException("Errore di tipo lessicale",e);
		}
		
		switch(tk.getTipo()) {
			case INT:{
				Token var = match(TokenType.INT);
				String value = var.getVal();
				return new NodeCost(value, LangType.TYINT);
			}
			case FLOAT:{
				Token var = match(TokenType.FLOAT);
				String value = var.getVal();
				return new NodeCost(value, LangType.TYFLOAT);
			}
			case ID:{
				Token var = match(TokenType.ID);
				NodeId Id = new NodeId(var.getVal());
				return new NodeDeref(Id);
			}
			default: 
				throw new SyntacticException("token " + tk + " alla riga " + tk.getRiga() + " deve essere un valore(intero o decimale) oppure un ID");
		}
	}
}
