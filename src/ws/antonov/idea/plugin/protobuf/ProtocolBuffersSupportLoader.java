package ws.antonov.idea.plugin.protobuf;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import ws.antonov.idea.plugin.protobuf.filetype.ProtoFileType;

/**
 * 
 */
public class ProtocolBuffersSupportLoader implements ApplicationComponent {
    public static final LanguageFileType PROTOCOL_BUFFERS = new ProtoFileType();

    public ProtocolBuffersSupportLoader() {
    }

    public void initComponent() {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(PROTOCOL_BUFFERS, "proto");
                    }
                }
        );
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "ProtocolBuffersSupportLoader";
    }
}
