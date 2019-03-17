grammar Ezuino;
@header {
package generated;
}
start:             dcls stmts
                ;
dcls:               dcl*
                ;
dcl:                type ID
                |   list
                ;



// TO BE ADDED :
//list

stmts:              stmt*
                ;

stmt:               ID ASSIGN expr
                |   func_call
                |   ID ASSIGN NOT? booleantf
                |   print_l
                |   LISTREMOVE'[' ']'
                |   LISTADD
                |   if_else
                |   while_stmt
                |   func
                |   switch_stmt
                |   list_add
                |   list_remove
                ;

func:               FUNCTION type? ID parameters block
                ;

func_call:   ID'('(expr? | expr(',' expr)+)')'
                ;

expr: val
|   func_call
|   expr PLUS expr
|   expr MINUS expr
|   expr MULTIPLE expr
|   expr DIVIDE expr
|   '(' expr ')'
;

print_l : PRINTSTMT expr;

//Mangler at implementere i staements
comparator_operator:      EQUAL
                |   NOTEQUAL
                |   LESS
                |   LESSTHANOREQUAL
                |   GREATER
                |   GREATERTHANOREQUAL
                ;
logic_operator:    AND
                | OR
                ;


condition:          boolean_expr (logic_operator boolean_expr)*
                |   booleantf
                ;
boolean_expr:        val comparator_operator val
                ;
val:                ID
                |   INTEGER
                |   DOUBLE
                |   STRING
                ;
type:               INTDCL
                |   DOUBLEDCL
                |   BOOLDCL
                |   STRINGDCL
                ;
booleantf:          TRUE
                |   FALSE
                ;
list_id:             ID
                ;
list_size:           '['INTEGER']'
                ;

switch_stmt:        SWITCH '(' val ')' block_switch
                ;

return_stmt:        RETURN expr
                ;

if_stmt:            IF'(' condition ')' block;

else_stmt:          ELSE block;

if_else:            if_stmt+ else_stmt?;

while_stmt:         WHILE'(' condition ')' block
                ;

parameters:         '(' param?(',' param)* ')'
                ;
param:              type ID
                ;

block:              SBRACE dcls stmts return_stmt? EBRACE
                ;
block_switch:       SBRACE (CASE val(',' val)*':' block)* (DEFAULT':' block)? EBRACE
                ;
list:               LISTDCL type list_id list_size ASSIGN '('(val','?)*')'
                ;

list_add:           LISTADD'('ID ',' val ',' INTEGER')'
                ;

list_remove:        LISTREMOVE'('ID ',' val ',' INTEGER')'
                ;

// DECLARATIONS
INTDCL              : 'int' ;
DOUBLEDCL           : 'double' ;
STRINGDCL           : 'string' ;
BOOLDCL             : 'boolean' ;
LISTDCL             : 'list' ;

// STATEMENTS
PRINTSTMT           : 'print' ;
ASSIGN              : ':=' ;
LISTADD             : 'list_add' ;
LISTREMOVE          : 'list_remove' ;

// OPERATORS
PLUS                : '+' ;
MINUS               : '-' ;
DIVIDE              : '/' ;
MULTIPLE            : '*' ;

// LOGICS
AND                 : 'AND' ;
OR                  : 'OR' ;
LESS                : '<' ;
GREATER             : '>' ;
EQUAL               : '=' ;
NOTEQUAL            : '!=' ;
NOT                 : '!';
LESSTHANOREQUAL     : '<=' ;
GREATERTHANOREQUAL  : '>=' ;

// CONDITIONALS
ELSE                : 'else' ;
IF                  : 'if' ;
WHILE               : 'while' ;
TRUE                : 'TRUE' ;
FALSE               : 'FALSE' ;
SWITCH              : 'switch' ;
CASE                : 'case' ;
RETURN              : 'return' ;
FUNCTION            : 'func' ;
DEFAULT             : 'default' ;



// IDENTIFIERS
ID                  : [a-zA-Z]+[a-zA-Z0-9]* ;
SBRACE              : '{' ;
EBRACE              : '}' ;

// DATA TYPES
INTEGER             : [0-9]+ ;
DOUBLE              : [0-9]+'.'[0-9]+ ;
STRING              : '"' (~["\r\n] | '""')* '"' ;
// EXTRA
BLANK               : [ \t\r\n]+ -> skip ;
COMMENT             : '#' ~[\r\n]*-> skip ;
