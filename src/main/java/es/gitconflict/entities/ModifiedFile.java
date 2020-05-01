package es.gitconflict.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ModifiedFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="FILE_ID" )
    private Long fileId;

    @Column( name="FILE_NAME", nullable = false)
    private String fileName;

    @Column(name="BRANCH_ID", nullable=false)
    private Long branchId;
}
