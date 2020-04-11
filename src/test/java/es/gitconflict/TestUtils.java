package es.gitconflict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestUtils {

    /**
     * Prepare the test environment creating a temporary folder and unziping the test-repo.zip file in the resources
     * folder into it.
     *
     * This way we can run the tests even from the jar file.
     *
     * @return Path to the created folder.
     */
    public static Path prepareEnvironment( String zipFile ) throws IOException
    {
        Path tempFolder = Files.createTempDirectory( getTestName() );
        tempFolder.toFile().deleteOnExit();

        URL resourceFolder = TestUtils.class.getResource( zipFile );
        String compressedRepo =  resourceFolder.getFile();

        try (FileInputStream fis = new FileInputStream(compressedRepo); ZipInputStream zis = new ZipInputStream( fis )) {
            ZipEntry zipEntry = null;
            while ( ( zipEntry = zis.getNextEntry() ) != null) {
                writeZipEntry( zis, tempFolder, zipEntry );
            }
            zis.closeEntry();
        }

        return tempFolder;
    }

    private static String getTestName()
    {
        StackTraceElement traza = new Throwable().getStackTrace()[1];
        return traza.getClassName() + "_" + traza.getMethodName();
    }

    /**
     * Writes a zipEntry to the destination folder.
     * @param destFolder
     * @param zipEntry
     */
    private static void writeZipEntry( ZipInputStream zis, Path destFolder, ZipEntry zipEntry ) throws IOException
    {
        File newFile = newFile( destFolder.toFile(), zipEntry);
        byte[] buffer = new byte[4096];

        if ( zipEntry.isDirectory() ) {
            newFile.mkdirs();
        }
        else {
            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            }
        }
    }

    /**
     * Creates a destination FILE form the destination folder and an entry from a zipfile.
     * @param destinationDir
     * @param zipEntry
     * @return
     * @throws IOException
     */
    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
