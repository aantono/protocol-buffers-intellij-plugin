package ws.antonov.idea.plugin.protobuf.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import ws.antonov.idea.plugin.protobuf.ProtoLanguage;

/**
 */
public class ProtoFileType extends LanguageFileType {
    public static final ProtoFileType PROTO_FILE_TYPE = new ProtoFileType();
    public static final Language PROTO_LANGUAGE = PROTO_FILE_TYPE.getLanguage();
    public static final Icon PROTO_LOGO = IconLoader.getIcon("/google.png");

    @NonNls
    public static final String PROTO_DEFAULT_EXTENSION = "proto";
    
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
        return PROTO_DEFAULT_EXTENSION;
    }

    @Nullable
    public Icon getIcon() {
        return PROTO_LOGO;
    }
}
