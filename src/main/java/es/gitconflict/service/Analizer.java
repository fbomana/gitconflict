package es.gitconflict.service;

import es.gitconflict.beans.Conflict;
import es.gitconflict.beans.GitRepository;
import es.gitconflict.entities.ChangeSet;

import java.io.IOException;
import java.util.List;

public interface Analizer 
{

	public ChangeSet collectData( GitRepository repo, String baseBranch ) throws IOException;
	
	public List<Conflict> findConflicts( ChangeSet changeSet );

}
