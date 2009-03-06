package ws.antonov.idea.plugin.protobuf.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import org.jetbrains.annotations.NotNull;

/**
 * 
 */
public class ProtoFileTypeLoader extends FileTypeFactory {
    public ProtoFileTypeLoader() {
        System.out.println("Creating ProtoFileTypeLoader");
    }

    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(ProtoFileType.PROTO_FILE_TYPE, ProtoFileType.PROTO_DEFAULT_EXTENSION);
    }
}