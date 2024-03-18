package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import token.*;

/**
 * La classe rappresenta lo scanner
 * 
 * @author marco
 */
public class Scanner {
	final char EOF = (char) -1; 
	private int riga;
	private PushbackReader buffer;
	private Token ultimoToken;
	
	final ArrayList<Character> skpChars = new ArrayList<Character>(Arrays.asList(' ','\n','\t','\r',EOF));
	
	final ArrayList<Character> letters = new ArrayList<Character>(Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

	final ArrayList<Integer> digits = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
	
	final HashMap<Character, TokenType> charTypeMap = new HashMap<>() {{
	    put('+', TokenType.PLUS);
	    put('-', TokenType.MINUS);
	    put('*', TokenType.TIMES);
	    put('/', TokenType.DIVIDE);
	    put(';', TokenType.SEMI);
	    put('=', TokenType.OP_ASSIGN); 
	}};
	
	final HashMap<String, TokenType> keyWordsMap = new HashMap<>() {{
		put("print", TokenType.PRINT);
		put("float", TokenType.TYFLOAT);
		put("int", TokenType.TYINT);
	}};
	
	/**
	 * Costruttore della classe Scanner
	 * 
	 * @param fileName percorso del file da analizzare
	 * @throws FileNotFoundException se il file non viene trovato
	 */
	public Scanner(String fileName) throws FileNotFoundException {
		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
	}
	
	/**
	 * Restituisce il token successivo consumandolo
	 * 
	 * @return il Token
	 * @throws IOException lancia un eccezione di input o output
	 * @throws LexicalException lancia un eccezione lessicale
	 */
	public Token nextToken() throws IOException, LexicalException {
		
		try {
			if(ultimoToken != null) {
				Token temporaneo = ultimoToken;
				ultimoToken = null;
				return temporaneo;
			}
			
			char nextChar;
			nextChar = peekChar();

			while(skpChars.contains(nextChar)) {
				if(nextChar == EOF) {
					Token token = new Token(TokenType.EOF, riga);
					return token;
				}
				if(nextChar == '\n'){
					riga++;
				}
				nextChar = readChar();
				nextChar=peekChar();
			}
			
			if(letters.contains(nextChar))
				return scanId();
			
			else if(charTypeMap.containsKey(nextChar)) {
				nextChar = readChar();
				
				if(nextChar == '=')
					return new Token(TokenType.OP_ASSIGN,riga,String.valueOf(nextChar));
				else if(peekChar() == '=' && nextChar != ';') {
					readChar();
					return new Token(TokenType.OP_ASSIGN,riga,String.valueOf(nextChar)+'=');
				}
				else
					return new Token(charTypeMap.get(nextChar),riga);
				}
			
			else if(digits.contains(Character.getNumericValue(nextChar)))
				return scanNumber();
			
			else {
				throw new LexicalException("Eccezione: " + readChar() + " riga: " + riga);
				}
			
			}catch(IOException e) {
				throw new LexicalException(e.getMessage(),e);
			}	
	}
	
	/**
	 * Restituisce il token successivo senza consumarlo
	 * 
	 * @return il Token
	 * @throws IOException lancia un eccezione di input o output
	 * @throws LexicalException lancia un eccezione lessicale
	 */
	public Token peekToken() throws IOException, LexicalException{
		
		if(ultimoToken == null) {
			ultimoToken = nextToken();
		}
		return ultimoToken;
	}

	private Token scanNumber() throws IOException, LexicalException {
		StringBuilder BuilderNumber= new StringBuilder();
		
		if(peekChar() == '0') {
			int contatore = 0;
			while(digits.contains(Character.getNumericValue(peekChar()))){
				BuilderNumber.append(readChar());
				contatore++;
			}
			if(peekChar() == '.') {
				BuilderNumber.append(readChar());
				return CostruisciFloat(BuilderNumber);
			}
			else if(letters.contains(peekChar())) {
				BuilderNumber.append(readChar());
				String Number = BuilderNumber.toString();
				throw new LexicalException("Eccezione: " + Number + " riga: " + riga);
			}else if(contatore == 1) {
				String Number = BuilderNumber.toString();
				return new Token(TokenType.INT, riga,Number);
			}
			else {
				String Number = BuilderNumber.toString();
				throw new LexicalException("Eccezione: " + Number + " riga: " + riga);
			}
		}
		else {
			while(digits.contains(Character.getNumericValue(peekChar()))) {
				BuilderNumber.append(readChar());
			}
			if(peekChar() == '.') {
				BuilderNumber.append(readChar());
				return CostruisciFloat(BuilderNumber);
			}
			else if(letters.contains(peekChar())) {
				BuilderNumber.append(readChar());
				String Number = BuilderNumber.toString();
				throw new LexicalException("Eccezione: " + Number + " riga: " + riga);
			}
			else {
				String Number = BuilderNumber.toString();
				return new Token(TokenType.INT, riga,Number);
			}
		}
	}

	private Token scanId() throws IOException, LexicalException  {
		StringBuilder BuilderID= new StringBuilder();
		
		while(letters.contains(peekChar())) {
			BuilderID.append(readChar());
		}
		
		if(digits.contains(Character.getNumericValue(peekChar()))) {
			BuilderID.append(readChar());
			String ID = BuilderID.toString();
			throw new LexicalException("Eccezione: " + ID + " riga: " + riga);
		}
			
		
		String ID = BuilderID.toString();
		
		if(keyWordsMap.containsKey(ID)) {
			return new Token(keyWordsMap.get(ID),riga,ID);
		}else {
			return new Token(TokenType.ID,riga,ID);
		}
	}

	private char readChar() throws IOException {
		return ((char) this.buffer.read());
	}

	private char peekChar() throws IOException {
		char c = (char) buffer.read();
		buffer.unread(c);
		return c;
	}
	
	private Token CostruisciFloat(StringBuilder BuilderNumber) throws IOException, LexicalException{
		int contatore = 0;
		
		while(digits.contains(Character.getNumericValue(peekChar())) &&  contatore <= 5){
			BuilderNumber.append(readChar());
			contatore++;
		}
		
		if(contatore > 5) {
			String Number = BuilderNumber.toString();
			throw new LexicalException("Eccezione: " + Number + " riga: " + riga);
		}
		if(letters.contains(peekChar())) {
			BuilderNumber.append(readChar());
			String Number = BuilderNumber.toString();
			throw new LexicalException("Eccezione: " + Number + " riga: " + riga);
		}
		
		String Number = BuilderNumber.toString();
		return new Token(TokenType.FLOAT,riga,Number);
		}
}	
