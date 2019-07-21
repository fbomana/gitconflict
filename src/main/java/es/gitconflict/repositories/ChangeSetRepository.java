package es.gitconflict.repositories;

import es.gitconflict.entities.ChangeSet;
import org.springframework.data.repository.Repository;

public interface ChangeSetRepository extends Repository <ChangeSet,Long> {
}
