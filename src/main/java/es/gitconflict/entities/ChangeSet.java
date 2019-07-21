package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class ChangeSet {

    @Id
    private Long changeSet;

    @Column(nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    private List<Branch> branches;

}
