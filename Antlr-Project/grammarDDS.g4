/**
 * Define a grammar called grammarDDS
 */
grammar grammarDDS;

indicador
   : term ((PLUS | MINUS) term)*
   ;

term
   : factor ((MULTI | DIV) factor)*
   ;

factor
   : variable
   | number
   | LPAREN indicador RPAREN
   ;

number
   : MINUS? DIGIT + (POINT DIGIT +)?
   ;

variable
   : LETTER (LETTER | DIGIT)*
   ;


LPAREN
   : '('
   ;


RPAREN
   : ')'
   ;


PLUS
   : '+'
   ;


MINUS
   : '-'
   ;


MULTI
   : '*'
   ;


DIV
   : '/'
   ;


POINT
   : '.'
   ;


LETTER
   : [a-zA-Z]+
   ;


DIGIT
   :  [0-9]+
   ;


WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
