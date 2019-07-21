package es.gitconflict.service.impl;

import es.gitconflict.beans.GitRepository;
import es.gitconflict.service.CommandLineUtils;
import es.gitconflict.service.GitService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitServiceComandLineImpl implements GitService
{
    @Autowired
    CommandLineUtils commandLine;

    @Override
    public List<String> findBrancheReferences(@NonNull GitRepository repo, @NonNull Pattern nameFilter) throws IOException {
        String unfiltered = commandLine.runCommandLine( "git branch", repo.getFullName());
        return  Arrays.stream( unfiltered.split("\n") ).map( String::trim ).filter( line -> nameFilter.matcher( line ).matches()).collect(Collectors.toList());
    }

    @Override
    public List<String> findUnmergedBranches(@NonNull GitRepository repo,@NonNull String baseBranch, boolean local,@NonNull Pattern nameFilter ) throws IOException {
        StringBuilder command = new StringBuilder ("git branch --no-merged ");
        if ( !local )
        {
            command.append(" -r origin/");
        }
        command.append( baseBranch );
        String unfiltered = commandLine.runCommandLine( command.toString(), repo.getFullName());
        return  Arrays.stream( unfiltered.split("\n") ).map( String::trim ).filter( line -> nameFilter.matcher( line ).matches()).collect(Collectors.toList());
    }

    @Override
    public List<String> findModifiedFiles(@NonNull GitRepository repo,@NonNull String branch, @NonNull String base ) throws IOException {
        String command = String.format ("git diff --name-only %s %s", branch, base );
        String files = commandLine.runCommandLine( command, repo.getFullName());
        List<String> result =   Arrays.stream( files.split("\n") ).map( String::trim ).collect(Collectors.toList());
        if ( result.size() == 1 && "".equals( result.get(0)))
        {
            result.clear();
        }
        return result;
    }

}
