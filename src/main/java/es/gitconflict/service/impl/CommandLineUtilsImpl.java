package es.gitconflict.service.impl;

import es.gitconflict.service.CommandLineUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Service
@Slf4j
public class CommandLineUtilsImpl implements CommandLineUtils {


    /**
     * Executes a command line and returns the standard output of the command line. It splits the line on whitespace char.
     * It's not ok, but sufices for our current development.
     *
     * @param commandLine the command line to execute.
     * @return
     */
    @Override
    public String runCommandLine(@NonNull final String commandLine) throws IOException {
        return runCommandLine(commandLine, null);
    }

    /**
     * Executes a command line and returns the standard output of the command line. It splits the line on whitespace char.
     * It's not ok, but sufices for our current development.
     *
     * @param commandLine the command line to execute.
     * @param folder      where the execution takes place. If nothing is indicated it uses the actual folder.
     * @return
     */
    @Override
    public String runCommandLine(@NonNull final String commandLine, final Path folder) throws IOException {
        Process process = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(commandLine.split(" "));
            if (folder != null) {
                pb.directory(folder.toFile());
            }

            process = pb.start();
            process.waitFor();
            return readTextStream(process.getInputStream());
        } catch (Exception e) {
            if (process != null) {
                log.error("Error executing command: {}\n {}", commandLine, readTextStream(process.getErrorStream()));
            }
            throw new IOException(e);
        }
    }

    private String readTextStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(is); BufferedReader buf = new BufferedReader(isr)) {
            String line = null;
            while ((line = buf.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}
