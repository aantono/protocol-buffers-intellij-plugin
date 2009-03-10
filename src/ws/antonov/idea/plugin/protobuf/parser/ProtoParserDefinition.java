package ws.antonov.idea.plugin.protobuf.parser;

import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lang.ASTNode;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import ws.antonov.idea.plugin.protobuf.lexer.ProtoFlexLexer;
import ws.antonov.idea.plugin.protobuf.lexer.ProtoTokenTypes;
import ws.antonov.idea.plugin.protobuf.file.ProtoFile;

/**
 * 
 */
public class ProtoParserDefinition implements ParserDefinition {

    @NotNull
    public Lexer createLexer(Project project) {
        return new ProtoFlexLexer();
    }

    public PsiParser createParser(Project project) {
        return new ProtoParser();
    }

    public IFileElementType getFileNodeType() {
        return ProtoElementTypes.FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create(ProtoTokenTypes.WHITE_SPACE, ProtoTokenTypes.EOL, ProtoTokenTypes.EOF);
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return ProtoTokenTypes.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return ProtoTokenTypes.STRINGS;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        final IElementType type = node.getElementType();

        if (type == ProtoElementTypes.FILE) {
            return new ProtoElement.File(node);
        } else if (type == ProtoElementTypes.PACKAGE) {
            return new ProtoElement.Package(node);
        } else if (type == ProtoElementTypes.MESSAGE) {
            return new ProtoElement.Message(node);
        } else if (type == ProtoElementTypes.OPTION) {
            return new ProtoElement.Option(node);
        /*} else if (type == ProtoElementTypes.VARIABLE) {
            return new ProtoElement.Symbol(node);
        } else if (type == ProtoElementTypes.DEFN) {
            return new ProtoElement.Defn(node);
        } else if (type == ProtoElementTypes.DEFNDASH) {
            return new ProtoElement.DefnDash(node);
        } else if (type == ProtoElementTypes.DEF) {
            return new ProtoElement.Def(node);*/
        } else if (type == ProtoElementTypes.ASSIGNMENT) {
            return new ProtoElement.Assignment(node);
        } else if (type == ProtoElementTypes.KEYWORD) {
            return new ProtoElement.Keyword(node);
        } else if (type == ProtoElementTypes.LITERAL) {
            return new ProtoElement.Literal(node);
        /*} else if (type == ProtoElementTypes.TOPLIST) {
            return new ProtoElement.TopList(node);
        } else if (type == ProtoElementTypes.LIST) {
            return new ProtoElement.List(node);
        } else if (type == ProtoElementTypes.VECTOR) {
            return new ProtoElement.Vector(node);
        } else if (type == ProtoElementTypes.MAP) {
            return new ProtoElement.Map(node);
        } else if (type == ProtoElementTypes.QUOTED_EXPRESSION) {
             return new ProtoElement.QuotedExpression(node);
        } else if (type == ProtoElementTypes.BACKQUOTED_EXPRESSION) {
             return new ProtoElement.BackQuotedExpression(node);
        } else if (type == ProtoElementTypes.POUND_EXPRESSION) {
            return new ProtoElement.Pound(node);
        } else if (type == ProtoElementTypes.UP_EXPRESSION) {
            return new ProtoElement.Up(node);
        } else if (type == ProtoElementTypes.POUNDUP_EXPRESSION) {
            return new ProtoElement.Metadata(node);
        } else if (type == ProtoElementTypes.TILDA_EXPRESSION) {
            return new ProtoElement.Tilda(node);
        } else if (type == ProtoElementTypes.AT_EXPRESSION) {
            return new ProtoElement.At(node);
        } else if (type == ProtoElementTypes.TILDAAT_EXPRESSION) {
            return new ProtoElement.TildaAt(node);*/
        }

        throw new Error("Unexpected ASTNode: " + node.getElementType());
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {

        if (
                /*left.getElementType() == ProtoTokenTypes.QUOTE
                        || left.getElementType() == ProtoTokenTypes.POUND
                        || left.getElementType() == ProtoTokenTypes.POUNDUP*/
                false
                ) {

            return SpaceRequirements.MUST_NOT;

        } else if (
                left.getElementType() == ProtoTokenTypes.LEFT_PAREN
                        || right.getElementType() == ProtoTokenTypes.RIGHT_PAREN
                        || left.getElementType() == ProtoTokenTypes.RIGHT_PAREN
                        || right.getElementType() == ProtoTokenTypes.LEFT_PAREN

                        || left.getElementType() == ProtoTokenTypes.LEFT_CURLY
                        || right.getElementType() == ProtoTokenTypes.RIGHT_CURLY
                        || left.getElementType() == ProtoTokenTypes.RIGHT_CURLY
                        || right.getElementType() == ProtoTokenTypes.LEFT_CURLY

                        || left.getElementType() == ProtoTokenTypes.LEFT_BRACKET
                        || right.getElementType() == ProtoTokenTypes.RIGHT_BRACKET
                        || left.getElementType() == ProtoTokenTypes.RIGHT_BRACKET
                        || right.getElementType() == ProtoTokenTypes.LEFT_BRACKET) {

            return SpaceRequirements.MAY;
        }
        return SpaceRequirements.MUST;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new ProtoFile(viewProvider);
    }
}