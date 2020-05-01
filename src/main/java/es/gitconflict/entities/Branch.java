package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BRANCH_ID")
    private Long branchId;

    @Column( name="BRANCH_NAME", nullable = false )
    private String branchName;

    @Column(name="CHANGE_SET_ID", nullable = false)
    private Long changeSetId;

    @OneToMany
    @JoinColumn( name = "FILE_ID")
    private List<ModifiedFile> files;
}
