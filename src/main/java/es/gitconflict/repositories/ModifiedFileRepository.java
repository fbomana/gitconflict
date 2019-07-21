package es.gitconflict.repositories;

import es.gitconflict.entities.ModifiedFile;
import org.springframework.data.repository.Repository;

public interface ModifiedFileRepository extends Repository<ModifiedFile, Long> {
}
