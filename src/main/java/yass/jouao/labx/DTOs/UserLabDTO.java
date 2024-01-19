package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

@Data
public class UserLabDTO {

	public interface saveUser {
	}

	private long id;
	@JsonView({ saveUser.class })
	private String name;
	@JsonView({ saveUser.class })
	private String password;
	@JsonView({ saveUser.class })
	private RoleUser userRole;
	@JsonView({ saveUser.class })
	private String information;
	@JsonView({ saveUser.class })
	private StatusUser status;
}
