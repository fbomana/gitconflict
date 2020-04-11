package es.gitconflict.repositories;

import es.gitconflict.entities.ModifiedFile;
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
public class ModifiedFileRepositoryIT
{
    @Autowired
    ModifiedFileRepository repository;

    @Test
    @Transactional
    public void crudTest()
    {
        Timestamp ts = new Timestamp( System.currentTimeMillis() );
        ModifiedFile modifiedFile = new ModifiedFile();
        modifiedFile.setFileName("test");
        modifiedFile.setFileId( 1L );

        ModifiedFile saved = repository.save( modifiedFile );
        assertNotNull( saved );

        ModifiedFile recovered = repository.getOne( 1L );
        assertNotNull( recovered );
        assertEquals( saved.getFileName(), recovered.getFileName());
        assertEquals( saved.getBranch(), recovered.getBranch() );

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
