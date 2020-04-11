package es.gitconflict;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GitConflictTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullArguments()
    {
        GitConflict.main( null );
    }

    @Test(expected = RuntimeException.class)
    public void zeroArguments()
    {
        GitConflict.main( new String[]{} );
    }

    @Test(expected = RuntimeException.class)
    public void wrongPath()
    {
        GitConflict.main( new String[]{"c:\\"} );
    }

    @Test(expected = RuntimeException.class)
    public void notAGitFolder()
    {
        GitConflict.main( new String[]{"c:\\temp"} );
    }

    @Test
    public void ok()
    {
        GitConflict.main( new String[]{"D:\\trabajo\\temp\\ATLAS_OPEN_C2J"});
    }

}
