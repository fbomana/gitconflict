package es.gitconflict.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public interface CommandLineUtils {

    /**
     * Executes a command line and returns the standard output of the command line. It splits the line on whitespace char.
     * It's not ok, but sufices for our current development.
     *
     * @param commandLine the command line to execute.
     * @return
     */
    public String runCommandLine(@NonNull final String commandLine) throws IOException;

    /**
     * Executes a command line and returns the standard output of the command line. It splits the line on whitespace char.
     * It's not ok, but sufices for our current development.
     *
     * @param commandLine the command line to execute.
     * @param folder      where the execution takes place. If nothing is indicated it uses the actual folder.
     * @return
     */
    public String runCommandLine(@NonNull final String commandLine, final Path folder) throws IOException;
}
