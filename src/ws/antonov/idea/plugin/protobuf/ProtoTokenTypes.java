package ws.antonov.idea.plugin.protobuf;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.TokenType;

/**
 * 
 */
public interface ProtoTokenTypes {
    //IElementType IDENTIFIER = new ProtoElementType("IDENTIFIER");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    IElementType PACKAGE = new ProtoElementType("PACKAGE");
    IElementType OPTION  = new ProtoElementType("OPTION");
    IElementType MESSAGE = new ProtoElementType("MESSAGE");

    IElementType ENUM = new ProtoElementType("ENUM");
    IElementType REQUIRED = new ProtoElementType("REQUIRED");
    IElementType OPTIONAL  = new ProtoElementType("OPTIONAL");
    IElementType REPEATED = new ProtoElementType("REPEATED");

    IElementType DEFAULT = new ProtoElementType("DEFAULT");

    TokenSet KEYWORDS = TokenSet.create(PACKAGE, OPTION, MESSAGE, ENUM, REQUIRED, OPTIONAL, REPEATED, DEFAULT);

    IElementType DOUBLE = new ProtoElementType("DOUBLE");
    IElementType FLOAT = new ProtoElementType("FLOAT");
    IElementType INT32 = new ProtoElementType("INT32");
    IElementType INT64 = new ProtoElementType("INT64");
    IElementType UINT32 = new ProtoElementType("UINT32");
    IElementType UINT64 = new ProtoElementType("UINT64");
    IElementType SINT32 = new ProtoElementType("SINT32");
    IElementType SINT64 = new ProtoElementType("SINT64");
    IElementType FIXED32 = new ProtoElementType("FIXED32");
    IElementType FIXED64 = new ProtoElementType("FIXED64");
    IElementType SFIXED32 = new ProtoElementType("SFIXED32");
    IElementType SFIXED64 = new ProtoElementType("SFIXED64");
    IElementType BOOL = new ProtoElementType("BOOL");
    IElementType STRING_TYPE = new ProtoElementType("STRING");
    IElementType BYTES = new ProtoElementType("BYTES");

    TokenSet TYPES = TokenSet.create(DOUBLE, FLOAT, INT32, INT64, UINT32, UINT64,
            SINT32, SINT64, FIXED32, FIXED64, SFIXED32, SFIXED64, BOOL, STRING_TYPE, BYTES);

    IElementType NUMERIC_LITERAL = new ProtoElementType("NUMERIC_LITERAL");
    IElementType STRING_LITERAL = new ProtoElementType("STRING_LITERAL");
    IElementType BOOLEAN_LITERAL = new ProtoElementType("BOOLEAN_LITERAL");

    IElementType LBRACE = new ProtoElementType("LBRACE");// {
    IElementType RBRACE = new ProtoElementType("RBRACE");// }
    IElementType LPAR = new ProtoElementType("LPAR");// (
    IElementType RPAR = new ProtoElementType("RPAR");// )
    IElementType LBRACKET = new ProtoElementType("LBRACKET");// [
    IElementType RBRACKET = new ProtoElementType("RBRACKET");// ]
    IElementType DOT = new ProtoElementType("DOT");// .
    IElementType SEMICOLON = new ProtoElementType("SEMICOLON");// ;
    IElementType COMMA = new ProtoElementType("COMMA");// , 

    IElementType EQ = new ProtoElementType("EQUALS");

    IElementType COMMENT = new ProtoElementType("COMMENT");

}
