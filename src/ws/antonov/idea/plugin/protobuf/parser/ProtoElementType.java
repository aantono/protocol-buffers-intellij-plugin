package ws.antonov.idea.plugin.protobuf.parser;

import com.intellij.psi.tree.IElementType;
import ws.antonov.idea.plugin.protobuf.file.ProtoFileType;

/**
 * 
 */
public class ProtoElementType extends IElementType {
    public ProtoElementType(String debugName) {
      super(debugName, ProtoFileType.PROTO_LANGUAGE);
    }

    public String toString() {
      return "PROTO:" + super.toString();
    }
  }
