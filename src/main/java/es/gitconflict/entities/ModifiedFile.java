package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Data
public class ModifiedFile
{
    @Id
    private Long fileId;

    @Column( nullable = false)
    private String fileName;

    @OneToMany
    private Branch branch;
}
