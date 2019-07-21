package es.gitconflict.service;

import java.util.List;

public interface ResultCollector
{
    /**
     * Recives a branch name and a list of modified files and stores it somewhere.
     * @param branch
     * @param modifiedFiles
     */
    public void collectResult( String branch, List<String> modifiedFiles );
}
