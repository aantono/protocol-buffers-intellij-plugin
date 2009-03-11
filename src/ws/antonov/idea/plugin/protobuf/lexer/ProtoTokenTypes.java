package ws.antonov.idea.plugin.protobuf.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.TokenType;
import ws.antonov.idea.plugin.protobuf.parser.ProtoElementType;

/**
 * 
 */
public interface ProtoTokenTypes {
    //IElementType IDENTIFIER = new ProtoElementType("IDENTIFIER");
    IElementType PACKAGE = new ProtoElementType("PACKAGE");
    IElementType OPTION  = new ProtoElementType("OPTION");

    IElementType FIELD_DEF = new ProtoElementType("FIELD_DEF");
    IElementType OBJECT_DEF = new ProtoElementType("OBJECT_DEF");

//    IElementType MESSAGE = new ProtoElementType("MESSAGE");
//    IElementType ENUM = new ProtoElementType("ENUM");
//    IElementType REQUIRED = new ProtoElementType("REQUIRED");
//    IElementType OPTIONAL  = new ProtoElementType("OPTIONAL");
//    IElementType REPEATED = new ProtoElementType("REPEATED");

//    IElementType DEFAULT = new ProtoElementType("DEFAULT");

    IElementType KEY = new ProtoElementType("KEY");

    TokenSet KEYWORDS = TokenSet.create(PACKAGE, OPTION, FIELD_DEF, OBJECT_DEF);

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

    TokenSet LITERALS = TokenSet.create(STRING_LITERAL, NUMERIC_LITERAL, BOOLEAN_LITERAL);

    IElementType LEFT_CURLY = new ProtoElementType("LEFT_CURLY");// {
    IElementType RIGHT_CURLY = new ProtoElementType("RIGHT_CURLY");// }
    IElementType LEFT_PAREN = new ProtoElementType("LEFT_PAREN");// (
    IElementType RIGHT_PAREN = new ProtoElementType("RIGHT_PAREN");// )
    IElementType LEFT_BRACKET = new ProtoElementType("LEFT_BRACKET");// [
    IElementType RIGHT_BRACKET = new ProtoElementType("RIGHT_BRACKET");// ]
    IElementType DOT = new ProtoElementType("DOT");// .
    IElementType SEMICOLON = new ProtoElementType("SEMICOLON");// ;
    IElementType COMMA = new ProtoElementType("COMMA");// , 

    IElementType EQUALS = new ProtoElementType("EQUALS");// =

    IElementType COMMENT = new ProtoElementType("COMMENT");
    TokenSet COMMENTS = TokenSet.create(COMMENT);

    // Control characters
    IElementType EOL = new ProtoElementType("end of line");
    IElementType EOF = new ProtoElementType("end of file");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    TokenSet WHITESPACE_SET = TokenSet.create(EOL, EOF, WHITE_SPACE);
    TokenSet STRINGS = TokenSet.create(STRING_LITERAL);
}
