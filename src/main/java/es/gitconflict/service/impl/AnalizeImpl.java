package es.gitconflict.service.impl;

import es.gitconflict.beans.Conflict;
import es.gitconflict.beans.GitRepository;
import es.gitconflict.entities.Branch;
import es.gitconflict.entities.ChangeSet;
import es.gitconflict.entities.ModifiedFile;
import es.gitconflict.repositories.BranchRepository;
import es.gitconflict.repositories.ChangeSetRepository;
import es.gitconflict.repositories.ModifiedFileRepository;
import es.gitconflict.service.Analizer;
import es.gitconflict.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;

public class AnalizeImpl implements Analizer
{

	@Autowired
	private GitService gitService;
	
	@Autowired
	private ChangeSetRepository changesetRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired 
	private ModifiedFileRepository modifiedFileRepository;
	
	private final Pattern patron = Pattern.compile( "origin/feature/*|origin/fix/*" );
	
	@Override
	public ChangeSet collectData( GitRepository repo, String baseBranch ) throws IOException
	{
		ChangeSet changeSet = new ChangeSet();
		changeSet.setTimestamp( new Timestamp( System.currentTimeMillis()));
		changeSet = changesetRepository.save( changeSet );
		
		List<String> branches = gitService.findUnmergedBranches(repo, baseBranch, false, patron );
		for ( String branch : branches )
		{
			Branch repoBranch = new Branch();
			repoBranch.setBranchName( branch );
			repoBranch.setChangeSetId( changeSet.getChangeSetId() );
			repoBranch = branchRepository.save( repoBranch );
			
			List<String> files = gitService.findModifiedFiles(repo, branch, baseBranch );
			for ( String file : files )
			{
				ModifiedFile modifiedFile = new ModifiedFile();
				modifiedFile.setBranchId( repoBranch.getBranchId() );
				modifiedFile.setFileName( file );
				modifiedFileRepository.save( modifiedFile );
			}
		}
		return changeSet;
	}

	@Override
	public List<Conflict> findConflicts(ChangeSet changeSet)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
