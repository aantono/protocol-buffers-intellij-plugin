package ws.antonov.idea.plugin.protobuf;

import com.intellij.psi.tree.IElementType;

/**
 * 
 */
public class ProtoElementType extends IElementType {
    public ProtoElementType(String debugName) {
      super(debugName, ProtocolBuffersSupportLoader.PROTOCOL_BUFFERS.getLanguage());
    }

    public String toString() {
      return "PROTO:" + super.toString();
    }
  }
