package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Branch {

    @Id
    private Long branchId;

    @Column( nullable = false )
    String branchName;

    @ManyToOne
    private ChangeSet changeSet;

    @OneToMany
    private List<ModifiedFile> files;
}
