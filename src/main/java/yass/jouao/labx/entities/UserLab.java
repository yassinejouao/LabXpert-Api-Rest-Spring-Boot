package yass.jouao.labx.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.RoleUser;
import yass.jouao.labx.enums.StatusUser;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLab {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = true)
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleUser userRole = RoleUser.NOTHING;
	private String information;
	@Enumerated(EnumType.STRING)
	private StatusUser status = StatusUser.ACTIVE;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Analysis> analysis;
}
