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

    public void parseIt( String text ) {
        PsiFile psiFile = createPseudoPhysicalFile(myProject, "test.proto", text);
        String psiTree = DebugUtil.psiToString(psiFile, false);
        System.out.println(psiTree);
    }

    public void testProtoFileType() {
        FileType fileType = FileTypeManager.getInstance().getFileTypeByFileName("foo.proto");
        assertNotNull(fileType);
    }

    public void testInteger() {
            String sym =
                    "123";
            parseIt(sym);
        }
}
