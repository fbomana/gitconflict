package es.gitconflict.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.gitconflict.entities.ModifiedFile;

public interface ModifiedFileRepository extends JpaRepository<ModifiedFile, Long> 
{
	
//	@Query( nativeQuery = true, value = "" )
//	public List<ModifiedFile> findConflicts( @Param( "change_id" ) Long id );
}
