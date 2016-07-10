grammar Level;
file : (tupel)*;
tupel : BracketOpen (SimpleValue | Separator)* BracketClose (LineBreak | EOF) ;
value : SimpleValue;
Separator : ';' | ',' ;
// line break maybe with preceding line feed
LineBreak : [\r\n]+;
// Only 'w' and 's' are allowed
SimpleValue : [0-9]+ ;

BracketOpen : '(' ;

BracketClose : ')';

// Skip Whitespaces
WS : [ \t]+ -> skip;