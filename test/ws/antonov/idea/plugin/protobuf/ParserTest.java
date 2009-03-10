package ws.antonov.idea.plugin.protobuf;

import junit.framework.TestCase;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture;
import com.intellij.testFramework.fixtures.TestFixtureBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.impl.DebugUtil;
import ws.antonov.idea.plugin.protobuf.parser.ProtoElement;
import ws.antonov.idea.plugin.protobuf.parser.ProtoElementType;
import ws.antonov.idea.plugin.protobuf.parser.ProtoElementTypes;
import ws.antonov.idea.plugin.protobuf.lexer.ProtoTokenTypes;


/**
 *
 */
public class ParserTest extends TestCase {
    protected Project myProject;
    protected Module myModule;
    protected IdeaProjectTestFixture myFixture;

    protected void setUp() {
        myFixture = createFixture();

        try {
            myFixture.setUp();
        }
        catch (Exception e) {
            throw new Error(e);
        }
        myModule = myFixture.getModule();
        myProject = myModule.getProject();
    }

    protected IdeaProjectTestFixture createFixture() {
        TestFixtureBuilder<IdeaProjectTestFixture> fixtureBuilder = IdeaTestFixtureFactory.getFixtureFactory().createLightFixtureBuilder();
        return fixtureBuilder.getFixture();
    }

    protected void tearDown() {
        try {
            myFixture.tearDown();
        }
        catch (Exception e) {
            throw new Error(e);
        }
    }

    private PsiFile createPseudoPhysicalFile(final Project project, final String fileName, final String text) throws IncorrectOperationException {
        FileType fileType = FileTypeManager.getInstance().getFileTypeByFileName(fileName);
        PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);

        return psiFileFactory.createFileFromText(
                fileName,
                fileType,
                text);
    }

    public PsiFile parseIt(String text) {
        PsiFile psiFile = createPseudoPhysicalFile(myProject, "test.proto", text);
        String psiTree = DebugUtil.psiToString(psiFile, false);
        System.out.println(psiTree);
        return psiFile;
    }

    public void testProtoFileType() {
        FileType fileType = FileTypeManager.getInstance().getFileTypeByFileName("foo.proto");
        assertNotNull(fileType);
    }

    public void testPackage() {
        String sym =
                "package ws.antonov.idea.plugin.proto;";
        PsiFile element = parseIt(sym);
        assertEquals(element.getChildren()[0].getClass(), ProtoElement.Package.class);
        assertEquals(element.getChildren()[0].getNode().getElementType(), ProtoElementTypes.PACKAGE);

        sym = "package ws.antonov.idea.plugin.proto";
        try {parseIt(sym); fail("Should get an error, no ; at the end"); } catch (Error e) {};
    }

    public void testOption() {
        String sym =
                "option optimize_for = SPEED;";
        PsiFile element = parseIt(sym);
        assertEquals(ProtoElement.Option.class, element.getChildren()[0].getClass());
        assertEquals(ProtoElementTypes.OPTION, element.getChildren()[0].getNode().getElementType());

        sym = "option optimize_for = SPEED";
        try {parseIt(sym); fail("Should get an error, no ; at the end"); } catch (Error e) {};
    }

    public void testMessage() {
        String sym =
                "message Foo {}";
        PsiFile element = parseIt(sym);
        assertEquals(element.getChildren()[2].getClass(), ProtoElement.Message.class);
        assertEquals(element.getChildren()[2].getNode().getElementType(), ProtoElementTypes.MESSAGE);
    }

    public void testString() {
        String sym =
                "\"SomeText\"";
        PsiFile element = parseIt(sym);
        assertEquals(element.getChildren()[0].getClass(), ProtoElement.Literal.class);
        assertEquals(element.getChildren()[0].getNode().getFirstChildNode().getElementType(), ProtoTokenTypes.STRING_LITERAL);
    }

    public void testInteger() {
        String sym =
                "123";
        PsiFile element = parseIt(sym);
        assertEquals(element.getChildren()[0].getClass(), ProtoElement.Literal.class);
        assertEquals(element.getChildren()[0].getNode().getFirstChildNode().getElementType(), ProtoTokenTypes.NUMERIC_LITERAL);
    }


}
