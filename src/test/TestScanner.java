package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.io.FileNotFoundException;

class TestScanner{

	@Test
	void testEOF() throws FileNotFoundException, IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testEOF.txt");
		Token token = new Token(TokenType.EOF, 4);
		assertEquals(s.nextToken().toString(), token.toString());
	}
	
	@Test 
	void testScanId() throws FileNotFoundException, IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testId.txt");
		Token token1 = new Token(TokenType.ID, 1,"jskjdsfhkjdshkf");
		Token token2 = new Token(TokenType.ID, 2,"printl");
		Token token3 = new Token(TokenType.ID, 4,"ffloat");
		Token token4 = new Token(TokenType.ID, 6,"hhhjj");
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token4.toString());
	}
	
	@Test 
	void testIdKeyWords() throws FileNotFoundException, IOException, LexicalException {
		Scanner s = new Scanner("src/test/data/testScanner/testIdKeyWords.txt");
		Token token1 = new Token(TokenType.TYINT, 1,"int");
		Token token2 = new Token(TokenType.ID, 1,"inta");
		Token token3 = new Token(TokenType.TYFLOAT, 2,"float");
		Token token4 = new Token(TokenType.PRINT, 3,"print");
		Token token5 = new Token(TokenType.ID, 4,"nome");
		Token token6 = new Token(TokenType.ID, 5,"intnome");
		Token token7 = new Token(TokenType.TYINT, 6,"int");
		Token token8 = new Token(TokenType.ID, 6,"nome");
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token4.toString());
		assertEquals(s.nextToken().toString(), token5.toString());
		assertEquals(s.nextToken().toString(), token6.toString());
		assertEquals(s.nextToken().toString(), token7.toString());
		assertEquals(s.nextToken().toString(), token8.toString());
	}
	
	@Test
	void testInt() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testINT.txt");
		Token token1 = new Token(TokenType.INT, 2,"698");
		Token token2 = new Token(TokenType.INT, 4,"560099");
		Token token3 = new Token(TokenType.INT, 5,"1234");
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
	}	
	
	@Test
	void testFloat() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testFLOAT.txt");
		Token token1 = new Token(TokenType.FLOAT, 1,"098.8095");
		Token token2 = new Token(TokenType.FLOAT, 2,"0.");
		Token token3 = new Token(TokenType.FLOAT, 3,"98.");
		Token token4 = new Token(TokenType.FLOAT, 5,"89.99999");
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token4.toString());
	}
	
	@Test 
	void testErroriNumbers() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/erroriNumbers.txt");
	
		LexicalException ex1  = assertThrows(LexicalException.class,()-> {
			s.nextToken();
		});
		LexicalException ex2  = assertThrows(LexicalException.class,()-> {
			s.nextToken();
		});
		LexicalException ex3  = assertThrows(LexicalException.class,()-> {
			s.nextToken();
		});
		LexicalException ex4  = assertThrows(LexicalException.class,()-> {
			s.nextToken();
		});
		
		
		assertEquals("Eccezione: 00 riga: 1",ex1.getMessage());
		assertEquals("Eccezione: 123a riga: 2",ex2.getMessage());
		assertEquals("Eccezione: 12.a riga: 3",ex3.getMessage());
		assertEquals("Eccezione: 123.121212 riga: 4",ex4.getMessage());
	}
	 
	@Test
	void testOperators() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testOperators.txt");
		Token token1 = new Token(TokenType.PLUS, 1);
		Token token2 = new Token(TokenType.OP_ASSIGN, 1,"/=");
		Token token3 = new Token(TokenType.MINUS, 2);
		Token token4 = new Token(TokenType.TIMES, 2);
		Token token5 = new Token(TokenType.DIVIDE, 3);
		Token token6 = new Token(TokenType.OP_ASSIGN, 5,"+=");
		Token token7 = new Token(TokenType.OP_ASSIGN, 6,"=");
		Token token8 = new Token(TokenType.OP_ASSIGN, 6,"-=");
		Token token9 = new Token(TokenType.MINUS, 8);
		Token token10 = new Token(TokenType.OP_ASSIGN, 8,"=");
		Token token11 = new Token(TokenType.OP_ASSIGN, 8,"*=");
		Token token12 = new Token(TokenType.SEMI, 10);
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token4.toString());
		assertEquals(s.nextToken().toString(), token5.toString());
		assertEquals(s.nextToken().toString(), token6.toString());
		assertEquals(s.nextToken().toString(), token7.toString());
		assertEquals(s.nextToken().toString(), token8.toString());
		assertEquals(s.nextToken().toString(), token9.toString());
		assertEquals(s.nextToken().toString(), token10.toString());
		assertEquals(s.nextToken().toString(), token11.toString());
		assertEquals(s.nextToken().toString(), token12.toString());
	}
	
	@Test
	void testGenerale() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testGenerale.txt");
		Token token1 = new Token(TokenType.TYINT, 1, "int");		
		Token token2 = new Token(TokenType.ID, 1, "temp");
		Token token3 = new Token(TokenType.SEMI, 1);
		Token token4 = new Token(TokenType.ID, 2, "temp");
		Token token5 = new Token(TokenType.OP_ASSIGN, 2, "+=");
		Token token6 = new Token(TokenType.FLOAT, 2, "5.");
		Token token7 = new Token(TokenType.SEMI, 2);
		Token token8 = new Token(TokenType.TYFLOAT, 4, "float");
		Token token9 = new Token(TokenType.ID, 4, "b");
		Token token10 = new Token(TokenType.SEMI, 4);
		Token token11 = new Token(TokenType.ID, 5, "b");
		Token token12 = new Token(TokenType.OP_ASSIGN, 5, "=");
		Token token13 = new Token(TokenType.ID, 5, "temp");
		Token token14 = new Token(TokenType.PLUS, 5);
		Token token15 = new Token(TokenType.FLOAT, 5, "3.2");
		Token token16 = new Token(TokenType.SEMI, 5);
		Token token17 = new Token(TokenType.PRINT, 6, "print");
		Token token18 = new Token(TokenType.ID, 6, "b");
		Token token19 = new Token(TokenType.SEMI, 6);
		Token token20 = new Token(TokenType.EOF, 7);
		
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token4.toString());
		assertEquals(s.nextToken().toString(), token5.toString());
		assertEquals(s.nextToken().toString(), token6.toString());
		assertEquals(s.nextToken().toString(), token7.toString());
		assertEquals(s.nextToken().toString(), token8.toString());
		assertEquals(s.nextToken().toString(), token9.toString());
		assertEquals(s.nextToken().toString(), token10.toString());
		assertEquals(s.nextToken().toString(), token11.toString());
		assertEquals(s.nextToken().toString(), token12.toString());
		assertEquals(s.nextToken().toString(), token13.toString());
		assertEquals(s.nextToken().toString(), token14.toString());
		assertEquals(s.nextToken().toString(), token15.toString());
		assertEquals(s.nextToken().toString(), token16.toString());
		assertEquals(s.nextToken().toString(), token17.toString());
		assertEquals(s.nextToken().toString(), token18.toString());
		assertEquals(s.nextToken().toString(), token19.toString());
		assertEquals(s.nextToken().toString(), token20.toString());
	}
	
	@Test
	void testPeek() throws FileNotFoundException, IOException, LexicalException{
		Scanner s = new Scanner("src/test/data/testScanner/testGenerale.txt");
		Token token1 = new Token(TokenType.TYINT, 1, "int");		
		Token token2 = new Token(TokenType.ID, 1, "temp");
		Token token3 = new Token(TokenType.SEMI, 1);
		Token token4 = new Token(TokenType.ID, 2, "temp");
	 
		assertEquals(s.peekToken().toString(), token1.toString());
		assertEquals(s.peekToken().toString(), token1.toString());
		assertEquals(s.nextToken().toString(), token1.toString());
		assertEquals(s.peekToken().toString(), token2.toString());
		assertEquals(s.nextToken().toString(), token2.toString());
		assertEquals(s.peekToken().toString(), token3.toString());
		assertEquals(s.peekToken().toString(), token3.toString());
		assertEquals(s.nextToken().toString(), token3.toString());
		assertEquals(s.peekToken().toString(), token4.toString());
		assertEquals(s.peekToken().toString(), token4.toString());
		assertEquals(s.peekToken().toString(), token4.toString());
		assertEquals(s.nextToken().toString(), token4.toString());	
	}
	
	
}
