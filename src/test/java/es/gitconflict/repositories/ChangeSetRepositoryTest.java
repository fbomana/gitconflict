package es.gitconflict.repositories;

import es.gitconflict.entities.ChangeSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeSetRepositoryTest {

	@Autowired
	public ChangeSetRepository repository;
	
	@Test
	public void findAllTest()
	{
		List<ChangeSet> lista = repository.findAll();
		assertNotNull( lista );
		assertTrue( lista.isEmpty() );
	}
}
