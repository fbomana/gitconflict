package es.gitconflict.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ModifiedFile
{
    @Id
    private Long fileId;

    @Column( nullable = false)
    private String fileName;

    @ManyToOne
    private Branch branch;
}
