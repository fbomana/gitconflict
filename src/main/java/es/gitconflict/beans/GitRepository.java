package es.gitconflict.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitRepository {
    Path location;
    String name;

    public Path getFullName()
    {
        return location.resolve( name );
    }
}
