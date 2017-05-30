/**
 * Define a grammar called grammarDDS
 */
grammar IndicatorGrammar;

@header{

}
prog
	: stat+
	;

stat
 	: expr NEWLINE						#expression
	| ID '=' expr NEWLINE				#assign
	| NEWLINE							#blank
	;


expr
 : MINUS expr                           #unaryMinusExpr
 | expr op=(MULT | DIV | MOD) expr      #multiplicationExpr
 | expr op=(PLUS | MINUS) expr          #additiveExpr
 | '(' expr ')' 						#paren
 | atom                                 #atomExpr
 ;

atom
 : (INT | FLOAT)  #numberAtom
 | ID             #idAtom
 | '$' INT		  #Account
 ;


PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
MOD : '%';

ID
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

INT
 : [0-9]+
 ;

FLOAT
 : [0-9]+ '.' [0-9]*
 | '.' [0-9]+
 ;

NEWLINE : '\r'? '\n' ;  // return newlines to parser (is end-statement signal

SPACE
 : [ \t\r\n] -> skip
 ;

