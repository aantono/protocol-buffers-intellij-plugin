package ws.antonov.idea.plugin.protobuf;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class ProtoLanguage extends Language {

    private static final String ID = "ProtocolBuffers";

    public ProtoLanguage() {
        super(ID);

        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            protected SyntaxHighlighter createHighlighter() {
                return new ProtoSyntaxHighlighter();
            }
        });
    }
    
    /*public ProtoLanguage() {
        super("ProtocolBuffers", "text/proto", "application/proto");

        *//*SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            protected SyntaxHighlighter createHighlighter() {
                return new ProtoSyntaxHighlighter();
            }
        });*//*

    }*/


}
