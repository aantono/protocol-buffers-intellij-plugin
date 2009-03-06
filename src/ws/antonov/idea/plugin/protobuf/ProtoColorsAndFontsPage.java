package ws.antonov.idea.plugin.protobuf;

import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

import ws.antonov.idea.plugin.protobuf.file.ProtoFileType;

/**
 * Created by IntelliJ IDEA.
 * User: aantonov
 * Date: Mar 6, 2009
 * Time: 2:48:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProtoColorsAndFontsPage implements ColorSettingsPage {
    @NotNull
    public String getDisplayName() {
        return "ProtocolBuffers";
    }

    @Nullable
    public Icon getIcon() {
        return ProtoFileType.PROTO_LOGO;
    }

    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return ATTRS;
    }

    private static final AttributesDescriptor[] ATTRS =
            new AttributesDescriptor[]{
                    new AttributesDescriptor(ProtoSyntaxHighlighter.COMMENT_ID, ProtoSyntaxHighlighter.PROTO_COMMENT),
                    /*new AttributesDescriptor(ClojureSyntaxHighlighter.ATOM_ID, ClojureSyntaxHighlighter.ATOM),
                    new AttributesDescriptor(ClojureSyntaxHighlighter.KEY_ID, ClojureSyntaxHighlighter.KEY),
                    new AttributesDescriptor(ClojureSyntaxHighlighter.NUMBER_ID, ClojureSyntaxHighlighter.NUMBER),
                    new AttributesDescriptor(ClojureSyntaxHighlighter.STRING_ID, ClojureSyntaxHighlighter.STRING),
                    new AttributesDescriptor(ClojureSyntaxHighlighter.BRACES_ID, ClojureSyntaxHighlighter.BRACES),
                    new AttributesDescriptor(ClojureSyntaxHighlighter.BAD_CHARACTER_ID, ClojureSyntaxHighlighter.BAD_CHARACTER),*/
            };

    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return new ColorDescriptor[0];
    }

    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new ProtoSyntaxHighlighter();
    }

    @NonNls
    @NotNull
    public String getDemoText() {
        return "package error;\n" +
                "\n" +
                "option java_package = \"com.orbitz.proto.error\";\n" +
                "option java_outer_classname = \"ErrorProtos\";\n" +
                "option java_multiple_files = true;\n" +
                "\n" +
                "option optimize_for = SPEED;\n" +
                "\n" +
                "message ErrorMessage {\n" +
                "  required string message = 1; // Error description\n" +
                "  optional string data = 2; // Stack trace or any other relevant information\n" +
                "}";
    }

    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        Map<String, TextAttributesKey> map = new HashMap<String, TextAttributesKey>();
        return map;
    }
}
