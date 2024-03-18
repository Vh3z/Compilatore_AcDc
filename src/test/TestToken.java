package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

class TestToken {

	@Test
	void CostruttoreTest() {
		Token token = new Token(TokenType.INT, 1, "12");
		assertEquals(token.getTipo(),TokenType.INT);
		assertEquals(token.getRiga(),1);
		assertEquals(token.getVal(), "12");	
		
		Token tokenB = new Token(TokenType.INT, 1);
		assertEquals(tokenB.getTipo(),TokenType.INT);
		assertEquals(tokenB.getRiga(),1);
		assertEquals(tokenB.getVal(), null);	
	}
	
	@Test
	void toStringTest() {
		Token token = new Token(TokenType.INT, 1, "12");
		assertEquals(token.toString(),"<INT,riga:1,12>");
	}
}
