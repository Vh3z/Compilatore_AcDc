package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;
import symbolTable.SymbolTable;
import visitor.CodeGenerationVisitor;
import visitor.TypeCheckVisitor;

class testCodeGeneration {
	
	@BeforeEach
	void init() {
		SymbolTable.init();
	}
	
	@Test
	void testDichiarazione() throws LexicalException, SyntacticException, IOException {
	    Scanner s = new Scanner("src/test/data/testCodeGenerator/testDichiarazione.txt");
	    Parser p = new Parser(s);
	    NodeProgram np = p.parse();
	    CodeGenerationVisitor cV = new CodeGenerationVisitor(); 
	    TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
	    np.accept(cV);
	    assertEquals("[, , , ]",cV.getCodici().toString());
	}

	
	@Test
	void testAssegnamento() throws LexicalException, SyntacticException, IOException {
		Scanner s = new Scanner("src/test/data/testCodeGenerator/testAssegnamento.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		CodeGenerationVisitor cV = new CodeGenerationVisitor();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		np.accept(tV);
		np.accept(cV);
		assertEquals("[ 5 sa 0 k,  3.5 sb 0 k]",cV.getCodici().toString());
	}
	
	@Test
	void testStampa() throws LexicalException, SyntacticException, IOException {
		Scanner s = new Scanner("src/test/data/testCodeGenerator/testStampa.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		CodeGenerationVisitor cV = new CodeGenerationVisitor();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		np.accept(cV);
		assertEquals("[ 9 sa 0 k, la p P ]",cV.getCodici().toString());
	}
	
	@Test
	void testCompleto() throws LexicalException, SyntacticException, IOException {
		Scanner s = new Scanner("src/test/data/testCodeGenerator/testCompleto.txt");
		Parser p = new Parser(s);
		NodeProgram np = p.parse();
		CodeGenerationVisitor cV = new CodeGenerationVisitor();
		TypeCheckVisitor tV = new TypeCheckVisitor();
		
		np.accept(tV);
		np.accept(cV);
		assertEquals("[, 1.0 5 k 6 /  sa 0 k, la p P ]",cV.getCodici().toString());
	}
	
	
	
	
}
