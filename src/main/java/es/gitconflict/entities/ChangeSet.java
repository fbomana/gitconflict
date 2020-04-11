package es.gitconflict.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ChangeSet {

    @Id
    private Long changeSet;

    @Column(nullable = false)
    private Timestamp timestamp;

    @OneToMany
    private List<Branch> branches;

}
