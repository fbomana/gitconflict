package es.gitconflict.service;

import java.io.IOException;
import java.util.List;

import es.gitconflict.beans.Conflict;
import es.gitconflict.beans.GitRepository;
import es.gitconflict.entities.ChangeSet;

public interface Analizer 
{

	public ChangeSet collectData( GitRepository repo, String baseBranch ) throws IOException;
	
	public List<Conflict> findConflicts( ChangeSet changeSet );

}
