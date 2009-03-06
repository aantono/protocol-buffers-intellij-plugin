package ws.antonov.idea.plugin.protobuf;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import ws.antonov.idea.plugin.protobuf.file.ProtoFileType;

/**
 *
 */
public class ProtoSupportLoader implements ApplicationComponent {
    public ProtoSupportLoader() {
    }

    public void initComponent() {
        System.out.println("Loading ProtoSupport");
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(ProtoFileType.PROTO_FILE_TYPE, ProtoFileType.PROTO_DEFAULT_EXTENSION);
                    }
                }
        );
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "ProtoSupportLoader";
    }
}