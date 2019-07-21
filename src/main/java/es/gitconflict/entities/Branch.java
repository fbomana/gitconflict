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

    @OneToMany
    private ChangeSet changeSet;

    @ManyToOne
    private List<ModifiedFile> files;
}
