package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLabDTO {

	public interface saveUser {
	}

	public interface viewUser {
	}

	@JsonView({ viewUser.class })
	private long id;
	@JsonView({ saveUser.class, viewUser.class })
	private String name;
	@JsonView({ saveUser.class })
	private String password;
	@JsonView({ saveUser.class, viewUser.class })
	private RoleUser userRole;
	@JsonView({ saveUser.class, viewUser.class })
	private String information;
	@JsonView({ saveUser.class, viewUser.class })
	private StatusUser status;
}
