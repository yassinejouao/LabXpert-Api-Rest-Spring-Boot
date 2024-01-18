package yass.jouao.labx.DTOs;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.Sex;

@Data
@NoArgsConstructor
@DynamicInsert
public class PatientDTO {

	public interface viewPatient {
	}

	public interface savePatient {
	}

	public interface updatePatient {
	}

	@JsonView({ viewPatient.class, updatePatient.class })
	private long id;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private String firstname;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private String lastname;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private LocalDate dateOfBirth;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private Sex sex;
	@JsonView({ viewPatient.class, savePatient.class, updatePatient.class })
	private String phone;
}
