package ws.antonov.idea.plugin.protobuf;

import com.intellij.lang.PairedBraceMatcher;
import com.intellij.lang.BracePair;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.antonov.idea.plugin.protobuf.lexer.ProtoTokenTypes;

/**
 * 
 */
public class ProtoBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(ProtoTokenTypes.LEFT_PAREN, ProtoTokenTypes.RIGHT_PAREN, true),
            new BracePair(ProtoTokenTypes.LEFT_BRACKET, ProtoTokenTypes.RIGHT_BRACKET, false),
            new BracePair(ProtoTokenTypes.LEFT_CURLY, ProtoTokenTypes.RIGHT_CURLY, false),
    };

    public BracePair[] getPairs() {
        return PAIRS;
    }

    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType lbraceType, @Nullable final IElementType tokenType) {
        return true;
    }

    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return 0;
    }
}
