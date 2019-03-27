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
    | if_stmt
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
primaryExpr
    : val
    | func_call
    ;
parenthesisExpr
    : primaryExpr
    | '(' expr ')'
    ;
unaryExpr
    : parenthesisExpr
    | unaryOperator parenthesisExpr
    ;
unaryOperator
    : '-'
    ;
multiplicativeExpr
    : unaryExpr
    | multiplicativeExpr operator=('*'|'/') unaryExpr
    ;
additiveExpr
    : multiplicativeExpr
    | additiveExpr operator=('+'|'-') multiplicativeExpr
    ;
relationalExpr
    : additiveExpr
    | relationalExpr operator=('<'|'>'|'<='|'>=') additiveExpr
    ;
equalityExpr
    : relationalExpr
    | equalityExpr operator=('='|'!=') relationalExpr
    ;
logicalAndExpr
    : equalityExpr
    | logicalAndExpr 'AND' equalityExpr
    ;
logicalOrExpr
    : logicalAndExpr
    | logicalOrExpr 'OR' logicalAndExpr
    ;
expr
    : logicalOrExpr
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
    : SWITCH '('val')' switch_block
    ;
return_stmt
    : RETURN expr
    ;
if_stmt
    : IF '('condition')' block
    | IF '('condition')' block ELSE block
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
switch_block
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