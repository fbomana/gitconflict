package es.gitconflict.repositories;

import es.gitconflict.entities.ChangeSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeSetRepository extends JpaRepository<ChangeSet,Long> {
}
