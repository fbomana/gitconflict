package es.gitconflict.service;

import es.gitconflict.beans.GitRepository;
import lombok.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public interface GitService
{
    /**
     * Get all local branchea names on a repo.
     * @param repo the repo that will be used
     * @param nameFilter name based regexp filter used to rule out all non matching branches.
     * @return List of branch references
     * @throws IOException
     */
    public List<String> findBrancheReferences( @NonNull GitRepository repo, @NonNull Pattern nameFilter ) throws IOException;

    /**
     * Find all branches that have not been merged on the basebranch.
     * @param repo the repo where the operation will be conducted.
     * @param baseBranch the basebranch where the branches will merge eventually
     * @param local select local or remote branches.
     * @param nameFilter name based regexp filter used to rule out all non matching branches.
     * @return List of branch references
     * @throws IOException
     */
    public List<String> findUnmergedBranches( @NonNull GitRepository repo, @NonNull String baseBranch, boolean local, @NonNull Pattern nameFilter ) throws IOException ;

    /**
     * Find all modified files between to git references ( branches, commits. --- ), the one with the changes and a base reference markinng the start of the divergence.
     * @param repo the repo where the operation will be conducted.
     * @param branch The branch with the changes
     * @param base the base commiit/reference
     * @return
     * @throws IOException
     */
    public List<String> findModifiedFiles(@NonNull GitRepository repo,@NonNull String branch, @NonNull String base ) throws IOException;
}
