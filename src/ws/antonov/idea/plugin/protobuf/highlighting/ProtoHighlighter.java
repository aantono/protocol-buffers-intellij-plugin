package ws.antonov.idea.plugin.protobuf.highlighting;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import ws.antonov.idea.plugin.protobuf.ProtoTokenTypes;
import ws.antonov.idea.plugin.protobuf.lexer._ProtobufLexer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ProtoHighlighter extends SyntaxHighlighterBase {
    private static Map<IElementType, TextAttributesKey> keys;

    @NotNull
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new _ProtobufLexer(true));
    }

    static final TextAttributesKey PROTO_KEYWORD = TextAttributesKey.createTextAttributesKey(
            "JS.KEYWORD",
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    static final TextAttributesKey PROTO_STRING = TextAttributesKey.createTextAttributesKey(
            "JS.STRING",
            SyntaxHighlighterColors.STRING.getDefaultAttributes()
    );

    static final TextAttributesKey PROTO_NUMBER = TextAttributesKey.createTextAttributesKey(
            "JS.NUMBER",
            SyntaxHighlighterColors.NUMBER.getDefaultAttributes()
    );

    static final TextAttributesKey PROTO_COMMENT = TextAttributesKey.createTextAttributesKey(
            "JS.LINE_COMMENT",
            SyntaxHighlighterColors.LINE_COMMENT.getDefaultAttributes()
    );


    static {
        keys = new HashMap<IElementType, TextAttributesKey>();

        fillMap(keys, ProtoTokenTypes.KEYWORDS, PROTO_KEYWORD);

        keys.put(ProtoTokenTypes.NUMERIC_LITERAL, PROTO_NUMBER);
        keys.put(ProtoTokenTypes.STRING_LITERAL, PROTO_STRING);
        keys.put(ProtoTokenTypes.COMMENT, PROTO_COMMENT);
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(keys.get(tokenType));
    }
}
