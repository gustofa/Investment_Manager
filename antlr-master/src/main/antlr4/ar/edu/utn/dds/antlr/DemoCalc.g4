grammar DemoCalc;

// expresion
indicador: TERMINO OPERADOR TERMINO ;

// reglas
OPERADOR: 'SUM' | 'RES' ;
TERMINO: [0-9]+ ;
WS: [ \t\r\n]+ -> skip ;