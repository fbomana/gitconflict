package es.gitconflict.service;

import es.gitconflict.TestUtils;
import es.gitconflict.beans.GitRepository;
import es.gitconflict.service.impl.GitServiceComandLineImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-test-context.xml")
public class GitServiceCommandLineImplTest {
    @Autowired
    GitServiceComandLineImpl service;

    @Test
    public void findBrancheReferences() throws IOException {
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");

        List<String> branches = service.findBrancheReferences(repo, Pattern.compile("^feature/.*"));
        assertNotNull(branches);
        assertFalse(branches.isEmpty());
        assertEquals(5, branches.size());
        for (String branch : branches) {
            assertTrue(branch.startsWith("feature/"));
        }
    }

    @Test
    public void findUnmergedBranches() throws IOException {

        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");

        List<String> branches = service.findUnmergedBranches(repo, "master", true, Pattern.compile("^feature/.*"));
        assertNotNull(branches);
        assertFalse(branches.isEmpty());
        assertEquals(4, branches.size());
        for (String branch : branches) {
            assertTrue(branch.startsWith("feature/"));
        }
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void findUnmergedBranchesNullBaseBranch() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("baseBranch is marked @NonNull but is null");
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");

        service.findUnmergedBranches(repo, null, true, Pattern.compile("^feature/.*"));
    }

    @Test
    public void findUnmergedBranchesNullRepo() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("repo is marked @NonNull but is null");

        service.findUnmergedBranches(null, "master", true, Pattern.compile("^feature/.*"));
    }

    @Test
    public void findUnmergedBranchesNullPattern() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("nameFilter is marked @NonNull but is null");
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");

        service.findUnmergedBranches(repo, "master", true, null);
    }

    @Test
    public void findUnmergedBranchesRemote() throws IOException {
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");

        List<String> branches = service.findUnmergedBranches(repo, "master", false, Pattern.compile("^feature/.*"));
        assertNotNull(branches);
        assertTrue(branches.isEmpty());

    }

    @Test
    public void findModifiedFisles() throws IOException {
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");
        List<String> files = service.findModifiedFiles(repo, "feature/unmerged1", "master");
        assertNotNull(files);
        assertFalse(files.isEmpty());
        assertTrue(files.size() == 2);
        assertEquals("file1.md", files.get(0) );
        assertEquals( "file2.md", files.get(1));
    }

    @Test
    public void findModifiedFislesNullBranch() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("branch is marked @NonNull but is null");

        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");
        service.findModifiedFiles(repo, null, "master");
    }

    @Test
    public void findModifiedFislesNullBase() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("base is marked @NonNull but is null");

        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");
        service.findModifiedFiles(repo, "feature/unmerged1", null);
    }

    @Test
    public void findModifiedFislesNullRepo() throws IOException {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("repo is marked @NonNull but is null");

        service.findModifiedFiles(null, "feature/unmerged1", "master");
    }

    @Test
    public void findModifiedFislesBadBRanch() throws IOException {
        GitRepository repo = new GitRepository(TestUtils.prepareEnvironment("/test-repo.zip"), "test-repo");
        List<String> files = service.findModifiedFiles(repo, "inventedName", "master");
        assertNotNull(files);
        assertTrue( files.isEmpty());
    }
}