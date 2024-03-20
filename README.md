# Compilatore_AcDc

Per Ac abbiamo:

  •	2 Tipi di dato: interi e floating point.
      Un intero è una sequenza di cifre che, se inizia per 0, non deve essere seguito da altre cifre.
      Un floating point è una sequenza di cifre (non vuota) seguita da un "." (punto) e da al massimo 5 cifre decimali.

  •	Variabili:
      Stringhe contenenti solo i 26 caratteri dell’alfabeto inglese minuscoli.
      Una variabile deve essere dichiarata prima di poter essere usata in un'espressione.

  •	Dichiarazioni: float o int seguiti da una variabile e da una eventuale inizializzazione.

  •	Espressioni:
      o	Letterali (interi o floating point).
      o	Variabili.
      o	Espressione op espressione, dove op (operatore binario) può essere +, -, * e /.
  Gli operandi di un'espressione binaria devono avere lo stesso tipo.
  Un'espressione di tipo int può essere convertita automaticamente a float (se necessario), e nessun'altra conversione è possibile.

  •	Istruzioni di assegnamento: variabile = espressione, oppure variabile op= espression.

  •	Istruzioni di stampa: print variabile.


Dc:
  
  Dc è una macchina a stack con dei registri che possono essee caricati dallo stack e il cui contenuto può essere memorizzato dall stack.
  L’istruzione sa fa il pop dello stack e lo mette in a e l’istruzione la fa il push sullo stack del registro a.
  
  Il comando p stampa il top dello stack senza fare il pop.
  Il comando P fa il pop dello stack.
  
  Per default, la precisione di dc è 0, ma per cambiarla (quando c’è una conversione a float), si può usare il comando k che imposta la precisione al numero sul top dello stack.
