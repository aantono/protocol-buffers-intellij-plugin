package ws.antonov.idea.plugin.protobuf.parser;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
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
    public static final IElementType EXTEND = new ProtoElementType("extend");

    public static final IElementType REQUIRED = new ProtoElementType("required");
    public static final IElementType OPTIONAL  = new ProtoElementType("optional");
    public static final IElementType REPEATED = new ProtoElementType("repeated");

    public static final TokenSet FIELDS = TokenSet.create(REQUIRED, OPTIONAL, REPEATED); 

    public static final IElementType DEFAULT = new ProtoElementType("default");

    public static final IElementType KEYWORD = new ProtoElementType("keyword");
    public static final IElementType ASSIGNMENT = new ProtoElementType("assignment");
    public static final IElementType LITERAL = new ProtoElementType("literal");
}
