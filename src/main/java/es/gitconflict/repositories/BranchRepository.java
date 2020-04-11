package es.gitconflict.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gitconflict.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
