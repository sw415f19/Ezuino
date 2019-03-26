grammar Ezuino;
@header {
package generated;
}
start
    : dcls stmts
    ;
dcls
    : dcl*
    ;
dcl
    : type ID
    | list
    | list_dcl
    ;
stmts
    : stmt*
    ;
stmt
    : assign_stmt
    | while_stmt
    | func_call
    | func_def
    | if_else
    | switch_stmt
    ;
assign_stmt
    : assign_expr
    | assign_booleantf
    | assign_condition
    ;
assign_expr
    : ID ASSIGN expr
    ;
assign_booleantf
    : ID ASSIGN NOT? booleantf
    ;
assign_condition
    : ID ASSIGN condition
    ;
expr
    : val
    | func_call
    | expr_brace
    | expr PLUS expr
    | expr MINUS expr
    | expr MULTIPLE expr
    | expr DIVIDE expr
    | expr logic_operator expr
    | expr comparator_operator expr
    ;
expr_brace
    : '('expr')'
    ;
func_def
    : FUNCTION type? ID parameters block;
func_call
    : ID '('func_call_param')'
    | built_in_func
    ;
func_call_param
    : expr? | expr(','expr)+
	;
built_in_func
    : print_l
    | list_add
    | list_remove
    ;
print_l
    : PRINTSTMT expr
    ;
comparator_operator
    : EQUAL
    | NOTEQUAL
    | LESS
    | LESSTHANOREQUAL
    | GREATER
    | GREATERTHANOREQUAL
    ;
logic_operator
    : AND
    | OR
    ;
condition
    : boolean_expr (logic_operator boolean_expr)*
    ;
boolean_expr
    : val comparator_operator val
    | booleantf
    ;
val
    : ID
    | INTEGER
    | DOUBLE
    | STRING
    ;
type
    : INTDCL
    | DOUBLEDCL
    | BOOLDCL
    | STRINGDCL
    ;
booleantf
    : TRUE
    | FALSE
    ;
switch_stmt
    : SWITCH '('val')' block_switch
    ;
return_stmt
    : RETURN expr
    ;
if_stmt
    : IF '('condition')' block
    ;
else_stmt
    : ELSE block
    ;
if_else
    : if_stmt+ else_stmt?
    ;
while_stmt
    : WHILE '('condition')' block
    ;
parameters
    : '('param?(','param)*')'
    ;
param
    : type ID
    ;
block
    : '{'dcls stmts return_stmt?'}'
    ;
block_switch
    : '{'(CASE val(','val)*':'block)* (DEFAULT':' block)?'}'
    ;
list
    : LISTDCL type ID '['INTEGER']'
    ;
list_dcl
    : LISTDCL type ID '['INTEGER']' ASSIGN '('(val','?)*')'
    ;
list_add
    : LISTADD '('ID','val','INTEGER')'
    ;
list_remove
    : LISTREMOVE '('ID','val','INTEGER')'
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
NOT                 : '!';
AND                 : 'AND' ;
OR                  : 'OR' ;
EQUAL               : '=' ;
NOTEQUAL            : '!=' ;
LESS                : '<' ;
LESSTHANOREQUAL     : '<=' ;
GREATER             : '>' ;
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
// DATA TYPES
INTEGER             : [0-9]+ ;
DOUBLE              : [0-9]+'.'[0-9]+ ;
STRING              : '"' (~["\r\n] | '""')* '"' ;
// EXTRA
BLANK               : [ \t\r\n]+ -> skip ;
COMMENT             : '#' ~[\r\n]*-> skip ;