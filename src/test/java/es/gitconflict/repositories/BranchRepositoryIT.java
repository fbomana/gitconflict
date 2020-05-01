package es.gitconflict.repositories;

import es.gitconflict.entities.Branch;
import es.gitconflict.entities.ChangeSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/spring-test-context.xml")
public class BranchRepositoryIT
{
    @Autowired
    private BranchRepository repository;

    @Autowired
    private ChangeSetRepository changeSetRepository;

    @Test
    @Transactional
    public void crudTest()
    {
        // Create the changeSet
        Timestamp ts = new Timestamp( System.currentTimeMillis() );
        ChangeSet changeSet = new ChangeSet();
        changeSet.setTimestamp( ts );
        changeSet = changeSetRepository.save( changeSet );

        Branch branch = new Branch();
        branch.setBranchName("test");
        branch.setChangeSetId( changeSet.getChangeSetId());

        Branch saved = repository.save( branch );
        assertNotNull( saved );

        Branch recovered = repository.getOne( saved.getBranchId() );
        assertNotNull( recovered );
        assertEquals( saved.getBranchName(), recovered.getBranchName());
        assertEquals( saved.getChangeSetId(), recovered.getChangeSetId() );

        repository.delete( recovered );
        try {
            recovered = repository.getOne( saved.getBranchId() );
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
}
