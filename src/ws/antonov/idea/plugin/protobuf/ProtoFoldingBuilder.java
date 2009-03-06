package ws.antonov.idea.plugin.protobuf;

import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;

import java.util.List;
import java.util.ArrayList;

import static ws.antonov.idea.plugin.protobuf.parser.ProtoElementTypes.*;

/**
 *
 */
public class ProtoFoldingBuilder implements FoldingBuilder {

    public String getPlaceholderText(ASTNode node) {

        if (node.getElementType() == MESSAGE) {
            return "message ...)";
        }/* else if (node.getElementType() == DEFN) {
            return "(defn "+((ClojureElement)(node.getPsi())).getName()+"  ...)";
        } else if (node.getElementType() == DEFNDASH) {
             return "(defn- "+((ClojureElement)(node.getPsi())).getName()+"  ...)";
        } else if (node.getElementType() == TOPLIST) {
             return "(...)";
        }*/
        throw new Error( "Unexpected node: " + node.getElementType() + "-->" + node.getText());
    }

    public boolean isCollapsedByDefault(ASTNode node) {
        return false;
    }

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        touchTree(node);
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /**
     * We have to touch the PSI tree to get the folding to show up when we first open a file
     */
    private void touchTree(ASTNode node) {
        if (node.getElementType() == FILE) {
            final PsiElement firstChild = node.getPsi().getFirstChild();
        }
    }

    private void appendDescriptors(final ASTNode node, final List<FoldingDescriptor> descriptors) {
        if (isFoldableNode(node)) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, descriptors);
            child = child.getTreeNext();
        }
    }

    private boolean isFoldableNode(ASTNode node) {
        return (
        (node.getElementType() == MESSAGE)
                || (node.getElementType() == ENUM)
                );
    }
}