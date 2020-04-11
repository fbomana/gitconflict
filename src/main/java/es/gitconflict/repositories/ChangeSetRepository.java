package es.gitconflict.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gitconflict.entities.ChangeSet;

public interface ChangeSetRepository extends JpaRepository<ChangeSet,Long> {
}
