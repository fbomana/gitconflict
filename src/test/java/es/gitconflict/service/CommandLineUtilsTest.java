package es.gitconflict.service;

import es.gitconflict.service.impl.CommandLineUtilsImpl;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUtilsTest {

    @Test
    public void runCommandLineOk1args() throws IOException {
        CommandLineUtils clu = new CommandLineUtilsImpl();
        String output = clu.runCommandLine("git --version");
        assertNotEquals("", output);
        assertTrue( output.startsWith("git version"));
    }

    @Test
    public void runCommandLineOk2args() throws IOException {
        CommandLineUtils clu = new CommandLineUtilsImpl();
        String output = clu.runCommandLine("git --version", null);
        assertNotEquals("", output);
        assertTrue( output.startsWith("git version"));
    }

    @Test(expected = IOException.class )
    public void runCommandLineNoOk() throws IOException {
        CommandLineUtils clu = new CommandLineUtilsImpl();
        clu.runCommandLine("thiscommandshouldnotexists");
    }
}
