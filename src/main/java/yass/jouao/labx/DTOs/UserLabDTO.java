package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

@Data
public class UserLabDTO {

	public interface updateUser {
	}

	public interface saveUser {
	}

	@JsonView({ updateUser.class })
	private long id;
	@JsonView({ saveUser.class, updateUser.class })
	private String name;
	@JsonView({ saveUser.class, updateUser.class })
	private String password;
	@JsonView({ saveUser.class, updateUser.class })
	private RoleUser userRole;
	@JsonView({ saveUser.class, updateUser.class })
	private String information;
	@JsonView({ saveUser.class, updateUser.class })
	private StatusUser status;
}
