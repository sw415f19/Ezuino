grammar Ezuino;
@header {
package generated;
}
start:             dcls stmts
                ;
dcls:               dcl dcls
				|	/* lambda */
                ;
dcl:                type ID
                |   list
                ;

stmts:              stmt stmts
				|	/* lambda */
                ;

stmt:               assign_stmt
				|   while_stmt
                |   func_call
				|   func_def
                |   if_else
                |   switch_stmt
                ;
assign_stmt:        ID ASSIGN expr
                ;

func_def:           FUNCTION type? ID parameters block
                ;

func_call:          ID'('(expr? | expr(',' expr)+)')'
				|   built_in_func
                ;

built_in_func:      print_l
                |   list_add
                |   list_remove
				;

expr:             	'(' expr ')'
				|	unOp expr
                |   expr binOp expr
                |   func_call
                |	val
                ;
                
                
binOp:			
					MULTIPLE
				|	DIVIDE
				|	PLUS
				|	MINUS
				|	AND
				|	OR
				|	EQUAL
				|	NOTEQUAL
				|	LESS
				|	LESSTHANOREQUAL
				|	GREATER
				|	GREATERTHANOREQUAL
                ;
unOp:				NOT
				|	MINUS
				;

print_l:            PRINTSTMT expr
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

if_stmt:            IF'(' expr ')' block
                ;

else_stmt:          ELSE block
                ;

if_else:            if_stmt+ else_stmt?
                ;

while_stmt:         WHILE'(' expr ')' block
                ;

parameters:         '(' dcls ')'
                ;

block:              SBRACE dcls stmts return_stmt? EBRACE
                ;
block_switch:       SBRACE (CASE val(',' val)*':' block)* (DEFAULT':' block)? EBRACE
                ;
list:               LISTDCL type ID list_size
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