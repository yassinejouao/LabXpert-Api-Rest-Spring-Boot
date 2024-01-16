package yass.jouao.labx.DTOs;

import java.util.Collection;

import lombok.Data;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

@Data
public class UserLabDTO {
	private long id;
	private String name;
	private String password;
	private RoleUser userRole;
	private String information;
	private StatusUser status;
	private Collection<AnalysisDTO> analysis;
}
