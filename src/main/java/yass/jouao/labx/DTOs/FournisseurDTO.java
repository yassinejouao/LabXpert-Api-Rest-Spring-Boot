package yass.jouao.labx.DTOs;

import java.util.Collection;

import lombok.Data;

@Data
public class FournisseurDTO {
	private long id;
	private String name;
	private Collection<ReagentDTO> reagents;
}
