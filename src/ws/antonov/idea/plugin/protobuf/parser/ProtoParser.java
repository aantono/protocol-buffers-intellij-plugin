package ws.antonov.idea.plugin.protobuf.parser;

import com.intellij.lang.PsiParser;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import static ws.antonov.idea.plugin.protobuf.lexer.ProtoTokenTypes.*;
import static ws.antonov.idea.plugin.protobuf.parser.ProtoElementTypes.*;

/**
 * Created by IntelliJ IDEA.
 * User: aantonov
 * Date: Mar 7, 2009
 * Time: 2:08:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProtoParser implements PsiParser {
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        builder.setDebugMode(true);
        PsiBuilder.Marker marker = builder.mark();
        for (IElementType token = builder.getTokenType(); token != null; token = builder.getTokenType()) {
            parseTopExpression(builder);
        }
        marker.done(FILE);
        return builder.getTreeBuilt();
    }

    /**
     * The only allowed top-level elements are:
     * <ul>
     * <li>package</li>
     * <li>option</li>
     * <li>message</li>
     * <li>enum</li>
     * </ul>
     * @param builder -
     */
    private void parseTopExpression(PsiBuilder builder) {
        IElementType token = builder.getTokenType();
        if (KEY == token) {
            parseTopKeyword(builder);
        } else if (OBJECT_DEF == token) {
            parseObjectDef(builder);
        /*if (LEFT_PAREN == token) {
            //parseList(builder);
        } else if (LEFT_BRACKET == token) {
            //parseVector(builder);
        } else if (LEFT_CURLY == token) {
            //parseMap(builder);
        } else if (QUOTE == token) {
            parseQuote(builder);
        } else if (BACKQUOTE == token) {
            parseBackQuote(builder);
        } else if (SYMBOL == token) {
            parseSymbol(builder);
        } else if (COLON_SYMBOL == token) {
            parseKey(builder);
*/
        } else if (LITERALS.contains(token)) {
            parseLiteral(builder);
        } else if (null != token) {
            syntaxError(builder, "Expected Object Def, Package or Option");
        }
    }

    private void parseExpressions(IElementType endToken, PsiBuilder builder) {
        for (IElementType token = builder.getTokenType(); token != endToken && token != null; token = builder.getTokenType()) {
            parseExpression(builder);
        }
        builder.advanceLexer();
    }   

    private void parseExpression(PsiBuilder builder) {
        IElementType token = builder.getTokenType();
        if (EQUALS == token) {
            parseAssignment(builder);
        } else if (KEY == token) {
            parseKeyword(builder);
        }
    }

    private void syntaxError(PsiBuilder builder, String msg) {
        String e = msg + ": " + builder.getTokenText();
        //System.out.println(e);
        builder.error(e);
        advanceLexerOrEOF(builder);
    }

    private void advanceLexerOrEOF(PsiBuilder builder) {
        if (builder.getTokenType() != null) builder.advanceLexer();
    }

    private PsiBuilder.Marker markAndAdvance(PsiBuilder builder) {
        PsiBuilder.Marker marker = builder.mark();
        builder.advanceLexer();
        return marker;
    }

    private void markAndAdvance(PsiBuilder builder, IElementType type) {
        markAndAdvance(builder).done(type);
    }

    private void internalError(String msg) {
        throw new Error(msg);
    }

    /* --- */
    /**
     * Handles Top-level keywords: package & option
     */
    private void parseTopKeyword(PsiBuilder builder) {
        if (builder.getTokenType() != KEY) internalError("Expected package or option");
        String token = builder.getTokenText();
        PsiBuilder.Marker marker = markAndAdvance(builder);
        if ("package".equals(token)) {
            parsePackageDef(builder);
            marker.done(ws.antonov.idea.plugin.protobuf.parser.ProtoElementTypes.PACKAGE);
        } else if ("option".equals(token)) {
            parseOptionDef(builder);
            marker.done(ws.antonov.idea.plugin.protobuf.parser.ProtoElementTypes.OPTION);
        } else {
            internalError("Expected package or option, but got " + token);
        }
        advanceLexerOrEOF(builder);
        parseTopExpression(builder);
    }

    private void parsePackageDef(PsiBuilder builder) {
        if (builder.getTokenType() != KEY) internalError("Expected package name");
        advanceLexerOrEOF(builder);
        if (builder.getTokenType() != SEMICOLON) internalError("Expected ;");
    }

    private void parseOptionDef(PsiBuilder builder) {
        if (builder.getTokenType() != KEY) internalError("Expected package name");
        markAndAdvance(builder, KEYWORD);
        if (builder.getTokenType() != EQUALS) internalError("Expected =");
        parseExpressions(SEMICOLON, builder);
    }

    /**
     * Handles Message or Enum declaration
     */
    private void parseObjectDef(PsiBuilder builder) {
        String token = builder.getTokenText();
        PsiBuilder.Marker marker;
        advanceLexerOrEOF(builder);
        if (token.equals("message")) {
            marker = markAndAdvance(builder);
            parseMessageDef(builder);
            
            marker.done(MESSAGE);
        }
        advanceLexerOrEOF(builder);
        parseTopExpression(builder);
    }

    private void parseMessageDef(PsiBuilder builder) {
        if (builder.getTokenType() != LEFT_CURLY) internalError("Expected {");
        advanceLexerOrEOF(builder);

        parseExpressions(RIGHT_CURLY, builder);
    }

    /**
     * Enter: Lexer is pointed at the beginning of any keyword
     * Exit: Lexer is pointed immediately after keyword
     */
    private void parseKeyword(PsiBuilder builder) {
        markAndAdvance(builder, KEYWORD);
    }

    /**
     * Enter: Lexer is pointed at '='
     * Exit: Lexer is pointed immediately after ';'
     */
    private void parseAssignment(PsiBuilder builder) {
        PsiBuilder.Marker marker = markAndAdvance(builder);
        parseExpressions(SEMICOLON, builder);
        marker.done(ASSIGNMENT);
    }

    /**
     * Enter: Lexer is pointed at literal
     * Exit: Lexer is pointed immediately after literal
     */
    private void parseLiteral(PsiBuilder builder) {
        markAndAdvance(builder, LITERAL);
    }
}
