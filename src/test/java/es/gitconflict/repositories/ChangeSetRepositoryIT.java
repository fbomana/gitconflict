package es.gitconflict.repositories;

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
public class ChangeSetRepositoryIT {

    @Autowired
    ChangeSetRepository repository;

    @Test
    @Transactional
    public void crudTest()
    {
        Timestamp ts = new Timestamp( System.currentTimeMillis() );
        ChangeSet changeSet = new ChangeSet();
        changeSet.setChangeSet( 1L );
        changeSet.setTimestamp( ts );

        ChangeSet saved = repository.save( changeSet );
        assertNotNull( saved );

        ChangeSet recovered = repository.getOne( 1L );
        assertNotNull( recovered );
        assertEquals( ts, recovered.getTimestamp());
        assertEquals( saved.getChangeSet(), recovered.getChangeSet() );
        assertEquals( saved.getTimestamp(), recovered.getTimestamp() );

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
