package es.gitconflict.beans;

import es.gitconflict.entities.Branch;
import es.gitconflict.entities.ModifiedFile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Conflict
{
	private List<ModifiedFile> files;
	private Branch branchA;
	private Branch branchB;
	
}
