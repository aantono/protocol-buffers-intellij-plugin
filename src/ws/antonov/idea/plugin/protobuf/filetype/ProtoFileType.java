package ws.antonov.idea.plugin.protobuf.filetype;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import ws.antonov.idea.plugin.protobuf.language.ProtoLanguage;

/**
 */
public class ProtoFileType extends LanguageFileType {
    
    public ProtoFileType() {
        super(new ProtoLanguage());
    }

    @NotNull
    @NonNls
    public String getName() {
        return "Protocol Buffers";
    }

    @NotNull
    public String getDescription() {
        return "Google Protocol Buffers";
    }

    @NotNull
    @NonNls
    public String getDefaultExtension() {
        return "proto";
    }

    @Nullable
    public Icon getIcon() {
        return IconLoader.getIcon("/google.png");
    }
}
