package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class ChangeSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHANGE_SET_ID")
    private Long changeSetId;

    @Column(name="TIMESTAMP", nullable = false)
    private Timestamp timestamp;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( name = "CHANGE_SET_ID")
    private List<Branch> branches;

}
