package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import ast.LangType;
import ast.NodeProgram;
import ast.TipoTD;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import symbolTable.SymbolTable;
import visitor.TypeCheckVisitor;

class testTypeChecking {

	@Test
	void testDicRipetute() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/1_dicRipetute.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals("Id già dichiarato",tV.getResType().getMsg());
	}
	
	@Test
	void testIdNonDec() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/2_idNonDec.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
	
		np.accept(tV);
		assertEquals("Id non dichiarato",tV.getResType().getMsg());
	}
	
	@Test
	void testIdNonDec2() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/3_idNonDec");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals("Id non dichiarato",tV.getResType().getMsg());
	}
	
	@Test
	void testTipoNonCompatibile() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/4_tipoNonCompatibile.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals("I tipi dell'assegnamento non sono compatibili",tV.getResType().getMsg());
	}
	
	@Test
	void testcorretto1() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/5_corretto.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals(SymbolTable.lookup("a").getTipo(),LangType.TYFLOAT);
		assertEquals(SymbolTable.lookup("b").getTipo(),LangType.TYINT);
		assertEquals(tV.getResType().getTipo(),TipoTD.OK);
	}
	
	@Test
	void testcorretto2() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/6_corretto.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals(SymbolTable.lookup("a").getTipo(),LangType.TYINT);
		assertEquals(SymbolTable.lookup("b").getTipo(),LangType.TYFLOAT);
		assertEquals(tV.getResType().getTipo(),TipoTD.OK);
		
		System.out.print(np.toString());
	}
	
	@Test
	void testcorretto3() throws FileNotFoundException, IOException, LexicalException, SyntacticException {
		Scanner s = new Scanner("src/test/data/testTypeChecking/7_corretto.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		assertEquals(SymbolTable.lookup("a").getTipo(),LangType.TYINT);
		assertEquals(SymbolTable.lookup("b").getTipo(),LangType.TYINT);
		assertEquals(SymbolTable.lookup("temp").getTipo(),LangType.TYFLOAT);
		assertEquals(tV.getResType().getTipo(),TipoTD.OK);
	}
	
}
