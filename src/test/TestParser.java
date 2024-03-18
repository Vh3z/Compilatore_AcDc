package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import scanner.*;
import parser.*;
import ast.*;

import org.junit.jupiter.api.Test;

class TestParser {
	
	@Test
	void testParserCorretto0() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto0.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> {p.parse();});
	}
	
	@Test
	void testParserCorretto1() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto1.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> {p.parse();});
	}
	
	@Test
	void testParserCorretto2() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserCorretto2.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> {p.parse();});
	}
	
	@Test
	void testParserEcc_0() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_0.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token OP_ASSIGN alla riga 1", ex.getMessage());
	}
	
	@Test
	void testParserEcc_1() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_1.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <TIMES,riga:2> alla riga 2 deve essere un valore(intero o decimale) oppure un ID", ex.getMessage());
	}
	
	@Test
	void testParserEcc_2() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_2.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <INT,riga:3,1> alla riga 3 non puo' essere inizio di riga", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_3() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_3.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token OP_ASSIGN alla riga 2", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_4() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_4.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token ID alla riga 2", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_5() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_5.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token ID alla riga 3", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_6() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_6.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token ID alla riga 4", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_7() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_7.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("aspettato token ID alla riga 2", ex.getMessage());	
	}
	
	@Test
	void testSoloDichPrint1() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testSoloDichPrint1.txt");
		Parser p = new Parser(s);
		assertDoesNotThrow(() -> {p.parse();});
	}
	
	@Test
	void testParserEcc_8() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_8.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <FLOAT,riga:1,3.2> alla riga 1 deve essere o ';' o un operatore di assegnamento!", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_9() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_9.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <TYINT,riga:3,int> alla riga 3 deve essere un valore(intero o decimale) oppure un id", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_10() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_10.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <TYINT,riga:2,int> alla riga 2 deve essere un operatore(non di assegnamento) oppure ';'", ex.getMessage());	
	}
	
	@Test
	void testParserEcc_11() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testParser/testParserEcc_11.txt");
		Parser p = new Parser(s);
		SyntacticException ex  = assertThrows(SyntacticException.class,()-> {
			p.parse();
		});
		assertEquals("token <PLUS,riga:2> alla riga 2 deve essere un valore(intero o decimale) oppure un ID", ex.getMessage());	
	}
	
	@Test
	void testAst1()throws FileNotFoundException, IOException, LexicalException, SyntacticException{
		Scanner s = new Scanner("src/test/data/testParser/testAst1.txt");
		Parser p = new Parser(s);
		
		NodeAST ast = p.parse();
		assertEquals("Program [int a, float c, int b, print c, print a, print b]",ast.toString());
	}
	
	@Test
	void testAst2()throws FileNotFoundException, IOException, LexicalException, SyntacticException{
		Scanner s = new Scanner("src/test/data/testParser/testAst2.txt");
		Parser p = new Parser(s);
		
		NodeAST ast = p.parse();
		System.out.print(ast.toString());
		assertEquals("Program [float b, int a, a = (TYINT)5, b = NodeBinOp [op=PLUS, left=a, right=(TYFLOAT)3.2], print b]",ast.toString());
	}
}
