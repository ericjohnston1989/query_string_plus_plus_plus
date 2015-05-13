grammar Query;

query : infix EOF;

infix     : unmarked;
unmarked  : or+;
or        : and (OR and)*;
and       : prefix (AND prefix)*;
prefix    : must | mustNot | term;
must      : PLUS term;
mustNot   : MINUS term;
term      : basicTerm | parenTerm;
basicTerm : TERM | OR | AND | PLUS;
parenTerm : LPAREN infix RPAREN;

OR     : 'OR' | '||';
AND    : 'AND' | '&&';
PLUS   : '+';
MINUS  : '-' | '!' | 'NOT';
LPAREN : '(';
RPAREN : ')';

WS    : [ \t\r\n]+ -> skip;      // skip spaces, tabs, newlines
TERM  : ~[ \t\r\n+\-!()]~[ \t\r\n()]*; // TERMs are basically everything else
