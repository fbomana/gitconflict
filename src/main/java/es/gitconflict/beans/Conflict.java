package es.gitconflict.beans;

import java.util.List;

import es.gitconflict.entities.Branch;
import es.gitconflict.entities.ModifiedFile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Conflict
{
	private List<ModifiedFile> files;
	private Branch branchA;
	private Branch branchB;
	
}
