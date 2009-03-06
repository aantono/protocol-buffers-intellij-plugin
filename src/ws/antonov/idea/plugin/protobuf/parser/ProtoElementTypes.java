package ws.antonov.idea.plugin.protobuf.parser;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IElementType;
import ws.antonov.idea.plugin.protobuf.file.ProtoFileType;

/**
 * 
 */
public class ProtoElementTypes {
    public static final IFileElementType FILE = new IFileElementType(ProtoFileType.PROTO_LANGUAGE);

    public static final IElementType PACKAGE = new ProtoElementType("package");
    public static final IElementType OPTION  = new ProtoElementType("option");
    public static final IElementType MESSAGE = new ProtoElementType("message");

    public static final IElementType ENUM = new ProtoElementType("enum");
    public static final IElementType REQUIRED = new ProtoElementType("required");
    public static final IElementType OPTIONAL  = new ProtoElementType("optional");
    public static final IElementType REPEATED = new ProtoElementType("repeated");
}
