grammar Ezuino;
@header {
package generated;
}
start
    : dcls stmts EOF
    |
    ;
dcls
    : dcl*
    ;
dcl
    : type ID
    | list_dcl
    ;
stmts
    : stmt*
    |
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
    : ID ASSIGN expr
    ;
primaryExpr
    : val
    | booleantf
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
val
    : ID
    | INTEGER
    | DOUBLE
    | STRING
    ;
booleantf
    : TRUE
    | FALSE
    ;
type
    : int_dcl
    | double_dcl
    | boolean_dcl
    | string_dcl
    ;
int_dcl
    : INTDCL
    ;
double_dcl
    : DOUBLEDCL
    ;
boolean_dcl
    : BOOLEANDCL
    ;
string_dcl
    : STRINGDCL
    ;
switch_stmt
    : SWITCH '('val')' switch_block
    ;
return_stmt
    : RETURN expr
    ;
if_stmt
    : IF '('expr')' block
    | IF '('expr')' block ELSE block
    ;
while_stmt
    : WHILE '('expr')' block
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
list_dcl
    : LISTDCL type ID '['INTEGER']'
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
BOOLEANDCL          : 'boolean' ;
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