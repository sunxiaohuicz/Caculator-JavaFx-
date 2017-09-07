grammar FA;

start:stat+;

stat: expr NEWLINE      # printExpr
| ID '=' expr NEWLINE   # assign
| NEWLINE               # blank
;

expr: expr op=('*'|'/') expr    # multiAndDivide
| expr op=('-'|'+') expr        # addAndSub
| INT                           # int
| ID                            # id
| '(' expr ')'                  # parents
;

ID:[a-zA-Z]+;
INT:[0-9]+;
NEWLINE:'\r'? '\n';
WS:[\t]+->skip;
MUL:'*';
DIV:'/';
ADD:'+';
SUB:'-';
