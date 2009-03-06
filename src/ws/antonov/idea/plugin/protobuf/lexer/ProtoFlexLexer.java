package ws.antonov.idea.plugin.protobuf.lexer;

import com.intellij.lexer.FlexAdapter;


/**
 * 
 */
public class ProtoFlexLexer extends FlexAdapter {
    public ProtoFlexLexer() {
        super(new _ProtobufLexer(false));
    }

    public ProtoFlexLexer(boolean highlighterMode) {
        super(new _ProtobufLexer(highlighterMode));
    }
}
