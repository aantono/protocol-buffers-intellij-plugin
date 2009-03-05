package ws.antonov.idea.plugin.protobuf.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ws.antonov.idea.plugin.protobuf.ProtoTokenTypes;

%%

%{
    public _ProtobufLexer(boolean highlightMode) {
      this((java.io.Reader)null);
      isHighlightModeOn = highlightMode;
    }

    private boolean isHighlightModeOn = false;

%}

%class _ProtobufLexer
%public
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
DecIntegerLiteral = 0 | [1-9][0-9]*
CommentOld     = "//" {InputCharacter}* {LineTerminator}
Comment="/""/"[^\r\n]*

DIGIT=[0-9]
OCTAL_DIGIT=[0-7]
HEX_DIGIT=[0-9A-Fa-f]

DECIMAL_INTEGER_LITERAL=(0|([1-9](0-9)*))
HEX_INTEGER_LITERAL=0[Xx]({HEX_DIGIT})*
IntegerLiteral={DECIMAL_INTEGER_LITERAL}|{HEX_INTEGER_LITERAL}

FloatingPointLiteral=({FLOATING_POINT_LITERAL1})|({FLOATING_POINT_LITERAL2})|({FLOATING_POINT_LITERAL3})|({FLOATING_POINT_LITERAL4})
FLOATING_POINT_LITERAL1=(0-9)+"."({DIGIT})*({EXPONENT_PART})?
FLOATING_POINT_LITERAL2="."({DIGIT})+({EXPONENT_PART})?
FLOATING_POINT_LITERAL3=({DIGIT})+({EXPONENT_PART})
FLOATING_POINT_LITERAL4=({DIGIT})+
EXPONENT_PART=[Ee]["+""-"]?({DIGIT})*

CRLF= [\ \t \f]* (\n | \r | \r\n)
QuotedLiteral="'"([^\\\'\r\n]|{ESCAPE_SEQUENCE}|\\{CRLF})*("'"|\\)?
DoubleQuotedLiteral=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE}|\\{CRLF})*(\"|\\)?
ESCAPE_SEQUENCE=\\[^\r\n]
GROUP = "[" [^\\]]* "]"

%state STRING
%state COMMENT
%state FIELD_LINE

%%

/* keywords */
<YYINITIAL> "package"           {return ProtoTokenTypes.PACKAGE;}
<YYINITIAL> "option"            {return ProtoTokenTypes.OPTION;}
<YYINITIAL> "message"           {return ProtoTokenTypes.MESSAGE;}

<YYINITIAL> "required"           {yybegin(FIELD_LINE); System.out.println(yytext()); return ProtoTokenTypes.REQUIRED;}
<YYINITIAL> "optional"           {yybegin(FIELD_LINE); return ProtoTokenTypes.OPTIONAL;}
<YYINITIAL> "repeated"           {yybegin(FIELD_LINE); return ProtoTokenTypes.REPEATED;}


/* comments */
  {Comment}                     { return ProtoTokenTypes.COMMENT; }

/* whitespace */
  {WhiteSpace}                  { return ProtoTokenTypes.WHITE_SPACE; }

<COMMENT> {
    {InputCharacter}	        {
                                    System.out.println(yytext());
                                }

	{LineTerminator}	        {
                                    yybegin(YYINITIAL); return ProtoTokenTypes.COMMENT;
                                }
}

{IntegerLiteral}|{FloatingPointLiteral}            { return ProtoTokenTypes.NUMERIC_LITERAL; }
    {DoubleQuotedLiteral}|{QuotedLiteral}              { return ProtoTokenTypes.STRING_LITERAL; }

<FIELD_LINE> {
    {LineTerminator}	        {
                                    yybegin(YYINITIAL); return ProtoTokenTypes.WHITE_SPACE;
                                }
}

/* do nothing fallback */
.|\n                             { /* ignore */ }
