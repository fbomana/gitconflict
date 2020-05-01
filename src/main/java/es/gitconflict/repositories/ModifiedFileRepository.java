package es.gitconflict.repositories;

import es.gitconflict.entities.ModifiedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModifiedFileRepository extends JpaRepository<ModifiedFile, Long> 
{

    @Query( "SELECT m FROM ModifiedFile m where m.branchId <> :branchId and m.fileId in ( SELECT f.fileId FROM ModifiedFile f WHERE f.branchId = :branchId )")
    public List<ModifiedFile> findConflicts( @Param("branchId") Long branchId );
}
