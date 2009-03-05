package ws.antonov.idea.plugin.protobuf.language;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;
import ws.antonov.idea.plugin.protobuf.highlighting.ProtoHighlighter;

/**
 *
 */
public class ProtoLanguage extends Language {
    
    public ProtoLanguage() {
        super("ProtocolBuffers", "text/proto", "application/proto");

        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            protected SyntaxHighlighter createHighlighter() {
                return new ProtoHighlighter();
            }
        });

    }


}
