package es.gitconflict.repositories;

import es.gitconflict.entities.Branch;
import es.gitconflict.entities.ChangeSet;
import es.gitconflict.entities.ModifiedFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-test-context.xml")
public class ModifiedFileRepositoryIT
{
    @Autowired
    private ModifiedFileRepository repository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    ChangeSetRepository changeSetRepository;

    @Test
    @Transactional
    public void crudTest()
    {
        // Create the changeSet
        Timestamp ts = new Timestamp( System.currentTimeMillis() );
        ChangeSet changeSet = new ChangeSet();
        changeSet.setTimestamp( ts );
        changeSet = changeSetRepository.save( changeSet );

        // Create the branch
        Branch branch = new Branch();
        branch.setBranchName("test");
        branch.setChangeSetId( changeSet.getChangeSetId() );
        branch = branchRepository.save( branch );

        ModifiedFile modifiedFile = new ModifiedFile();
        modifiedFile.setFileName("test");
        modifiedFile.setBranchId( branch.getBranchId() );

        ModifiedFile saved = repository.save( modifiedFile );
        assertNotNull( saved );

        ModifiedFile recovered = repository.getOne(saved.getFileId() );
        assertNotNull( recovered );
        assertEquals( saved.getFileName(), recovered.getFileName());
        assertEquals( saved.getBranchId(), recovered.getBranchId() );

        repository.delete( recovered );
        try {
            recovered = repository.getOne( saved.getFileId() );
            fail();
        }
        catch ( javax.persistence.EntityNotFoundException | JpaObjectRetrievalFailureException e )
        {
            System.out.println("Finaliza ok");
        }
        catch ( Exception e ) {
            System.out.println("Erropr inexperado");
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void findConflicts()
    {
        List<ModifiedFile> files = repository.findConflicts( 1L );
        assertNotNull( files );
        assertTrue( files.isEmpty() );
    }
}
