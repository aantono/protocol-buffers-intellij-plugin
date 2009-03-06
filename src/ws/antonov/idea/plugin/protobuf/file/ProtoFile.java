package ws.antonov.idea.plugin.protobuf.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.psi.FileViewProvider;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.NotNull;

/**
 * 
 */
public class ProtoFile extends PsiFileBase {

    public ProtoFile(FileViewProvider viewProvider) {
        super(viewProvider, ProtoFileType.PROTO_LANGUAGE);
    }

    @NotNull
    public FileType getFileType() {
        return ProtoFileType.PROTO_FILE_TYPE;
    }

    @NotNull
    public String getPackageName() {
        return "";
    }

    public boolean isScript() {
        return true;
    }

    public boolean isJVMDebuggingSupported() {
        return true;
    }
}
