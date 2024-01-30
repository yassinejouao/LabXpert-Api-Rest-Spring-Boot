package yass.jouao.labx.entities;

import java.time.LocalDate;
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
import yass.jouao.labx.enums.Sex;
import yass.jouao.labx.enums.StatusField;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sex sex;
	@Column(nullable = false)
	private String phone;
	@Enumerated(EnumType.STRING)
	private StatusField status = StatusField.ACTIVE;
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private Collection<Sample> samples;
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private Collection<Analysis> analysis;
}
