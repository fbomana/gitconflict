package es.gitconflict.repositories;

import es.gitconflict.entities.Branch;
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
    BranchRepository repository;

    @Test
    @Transactional
    public void crudTest()
    {
        Timestamp ts = new Timestamp( System.currentTimeMillis() );
        Branch branch = new Branch();
        branch.setBranchName("test");
        branch.setBranchId( 1L );

        Branch saved = repository.save( branch );
        assertNotNull( saved );

        Branch recovered = repository.getOne( 1L );
        assertNotNull( recovered );
        assertEquals( saved.getBranchName(), recovered.getBranchName());
        assertEquals( saved.getChangeSet(), recovered.getChangeSet() );

        repository.delete( recovered );
        try {
            recovered = repository.getOne( 1L );
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
